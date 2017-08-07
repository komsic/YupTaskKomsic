package com.example.brainbell.yuptaskkomsic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.concurrent.TimeUnit;

public class TrailerAudioActivity extends AppCompatActivity {

    ImageButton playPauseImageButton;
    TextView startTimeTxt;
    TextView stopTimeTxt;
    SeekBar seekBar;

    //change listener for seekbar
    private SeekBar.OnSeekBarChangeListener mOnSeekBarChangeListener =
            new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            formatTime(progress);
            // save the progress to the currentTime class variable
            currentTime = progress;
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            if (mMediaPlayer != null) {
                mMediaPlayer.seekTo(currentTime);
                mMediaPlayer.start();
            } else {
                Toast.makeText(TrailerAudioActivity.this, "MediaPlayer is null",
                        Toast.LENGTH_SHORT).show();
            }
        }
    };

    private MediaPlayer mMediaPlayer;

    // these class variables are used for determining the status of mediaPlayer (ie whether playing or not)
    private final int PLAY = -1;
    private final int PAUSE = -2;
    private int status;

    // these class variables are used for setting seekbar length... whatever
    // they are also use for tracking mediaPlayer
    private double startTime = 0;
    private double finalTime = 0;
    private int currentTime = 0;

    // Use to start a new thread for updating the {@startTimeTxt}
    private Handler myHandler = new Handler();
    private int forwardTime = 5000;
    private int backwardTime = 5000;

    //to set the seekBar max value one time only
    private int oneTimeOnly = 0;

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();

            //go back to previous activity
            finish();
        }
    };

    /** Handles audio focus when playing a sound file */
    private AudioManager mAudioManager;

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(currentTime);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // RESUME PLAYBACK
                        mMediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        releaseMediaPlayer();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                        duckMediaPlayer(true);
                    }
                }
            };

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailer_audio);

        // Create and setup {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        startTimeTxt = (TextView) findViewById(R.id.starting_time_txt);
        stopTimeTxt = (TextView) findViewById(R.id.stopping_time_txt);
        seekBar = (SeekBar) findViewById(R.id.seek_bar);
        seekBar.setClickable(true);
        seekBar.setEnabled(true);
        seekBar.setOnSeekBarChangeListener(mOnSeekBarChangeListener);

        //realease mediaPlayer resources b4 starting a new stream
        releaseMediaPlayer();

        // Request audio focus for playback
        int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                // Use the music stream.
                AudioManager.STREAM_MUSIC,
                // Request audio focus for unknown duration.
                AudioManager.AUDIOFOCUS_GAIN);

        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            // We have an audio focus now
            Bundle bundle = getIntent().getExtras();
            if (bundle == null) {
                finish();
            } else {
                LinearLayout rootLayout = (LinearLayout) findViewById(R.id.root_layer_trailer);
                rootLayout.setBackgroundResource(bundle.getInt("imageResourceId", 0));

                // start the music stream
                mMediaPlayer = MediaPlayer.create(this, bundle.getInt("audioResourceId", 0));

                // get the duration of the stream
                finalTime = mMediaPlayer.getDuration();
                startTime = mMediaPlayer.getCurrentPosition();

                // set the seekBar's length to finalTime and ensure its only set once
                if (oneTimeOnly == 0) {
                    seekBar.setMax((int) finalTime);
                    oneTimeOnly = 1;
                }

                // format startTimeTxt in X:XX
                startTimeTxt.setText(String.format("%d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                        finalTime)))
                );

                // format stopTimeTxt in X:XX
                stopTimeTxt.setText(String.format("%d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                        finalTime)))
                );

                //ensure volume change using hardware volume control
                setVolumeControlStream(AudioManager.STREAM_MUSIC);


                playPauseImageButton = (ImageButton) findViewById(R.id.play_pause_btn);

                mMediaPlayer.start();
                status = PLAY;

                // update the
                myHandler.postDelayed(UpdateSongTime,100);

                mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
            }
        }
    }

    public void onClickPlayPauseBtn(View view) {
        if (status == PLAY) {
            if (mMediaPlayer != null) {
                status = PAUSE;
                mMediaPlayer.pause();
                playPauseImageButton.setImageResource(R.drawable.ic_play);
            } else {

            }
        } else if (status == PAUSE) {
            status = PLAY;
            playPauseImageButton.setImageResource(R.drawable.ic_pause);
            mMediaPlayer.start();
        }
    }

    public void onClickRewindBtn(View view) {

        if (mMediaPlayer.getCurrentPosition() >= backwardTime) {
            mMediaPlayer.seekTo(mMediaPlayer.getCurrentPosition() - backwardTime);
            mMediaPlayer.start();
            playPauseImageButton.setImageResource(R.drawable.ic_pause);
            Toast.makeText(this, mMediaPlayer.getCurrentPosition() + "", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickForwardBtn(View view) {
        if (mMediaPlayer.getCurrentPosition() < (finalTime - forwardTime)) {
            mMediaPlayer.seekTo(mMediaPlayer.getCurrentPosition() + forwardTime);
            mMediaPlayer.start();
            playPauseImageButton.setImageResource(R.drawable.ic_pause);
            Toast.makeText(this, mMediaPlayer.getCurrentPosition() + "", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }

    @SuppressLint("DefaultLocale")
    private void formatTime(int initialTime) {
        startTimeTxt.setText(String.format("%d:%02d",
                TimeUnit.MILLISECONDS.toMinutes((long) initialTime),
                TimeUnit.MILLISECONDS.toSeconds((long) initialTime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                toMinutes((long) initialTime)))
        );
    }

    private synchronized void duckMediaPlayer(boolean duck) {
        mMediaPlayer.setVolume(duck ? 0.5f : 1.0f, duck ? 0.5f : 1.0f);
    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            if (mMediaPlayer != null) {
                startTime = mMediaPlayer.getCurrentPosition();
                formatTime((int)startTime);
                currentTime = mMediaPlayer.getCurrentPosition();
                seekBar.setProgress((int)startTime);
                myHandler.postDelayed(this, 100);
            }
        }
    };
}
