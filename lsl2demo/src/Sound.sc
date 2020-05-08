;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	SOUND.SC
;;;;	(c) Sierra On-Line, Inc, 1988
;;;;
;;;;	Author: Jeff Stephenson
;;;;
;;;;	The Sound class, which is used to play music.
;;;;
;;;;	Classes:
;;;;		Sound



(script#	MUSIC)
(include game.sh)
(use Main)
(use System)


(class Sound kindof Object
	;;; The class which plays music.  Sounds which have been init:ed have
	;;; an internal kernel node allocated for them which stores information
	;;; which needs to be accessed quickly.  This information can only be
	;;; changed by the changeState: method or another init: of the Sound.

	;;; Sounds are prioritized, with the highest priority sound which has
	;;; been issued the play: message being the sound which is actually played.
	;;; All other sounds are in a blocked (SND_BLOCKED) state, and when the
	;;; current sound is finished, the highest priority  blocked sound is
	;;; restarted.

	;;; Sounds can have built-in 'cue's to help in synchronizing animation
	;;; with the music.

	;;; Sounds may be 'owned' by Rooms or Regions, and an 'owned' Sound is
	;;; dispose:d whenever its owner is dispose:d.  A Sound with no owner
	;;; is dispose:d whenever any Room or Region is dispose:d.

	(properties
		state SND_NOTREADY
				;SND_NOTREADY
				;	Sound has not been init:ed, or has finished playing.
				;SND_READY
				;	Sound has been init:ed, but has not been sent the play: message.
				;SND_BLOCKED
				;	Sound has been sent the play: message, but either a higher
				;	priority sound is playing or sounds are paused.
				;SND_ACTIVE
				;	This is the currently playing Sound.
		number 0			;the number of the sound
		priority 0		;priority of the sound (normal == 0)
		loop 1			;the number of times to play the sound in succession
		handle 0			;pointer to the internal kernel node of the sound
		signal 0			;this is set (briefly) to the value of an animation cue
							;encountered by the sound
		prevSignal 0	;this contains the value of the last animation cue
							;encountered by the sound
		client 0			;the object to cue: on animation cues or sound completion
		owner 0			;the ID of the Room or Region which 'owns' this sound
	)

;;;	(methods
;;;		play				;play the sound
;;;		playMaybe		;play the sound if it has highest priority, dispose otherwise
;;;		stop				;stop the sound from playing
;;;		check				;private -- check for cues and respond accordingly
;;;		pause				;pause all sounds -- this is a class method
;;;		changeState		;change loop property in the internal kernel node
;;;		clean				;private -- dispose: yourself if your owner is being dispose:d.
;;;		fade				;fade music, signal done at 0 volume
;;;	)


	(method (new who)
		;; Return a new Sound with an optional owner.

		(return
			((super new:)
				owner: (if argc who else 0),
				yourself:
			)
		)
	)


	(method (init)
		;; Initialize a sound.  Allocate an internal sound node, set it up,
		;; and add it to the sound list, but do not start playing the sound.
		;; After this call, the sound state is SND_READY.

		(= signal 0)
		(= state SND_NOTREADY)
		(sounds add: self)
		(DoSound InitSound self)
	)


	(method (play caller &tmp oldInfo)
		;; Play the sound with an optional client (object to cue: on animation
		;; cues or when done).  After this call, the sound state is either
		;; SND_ACTIVE (if the sound is a high enough priority to be played)
		;; or SND_BLOCKED (if a higher priority sound is playing).

		;Stop this sound if it is currently playing.
		(self stop:)
		
		;If no loop, we probably want one.
		(if (not loop)
			(= loop 1)
		)

		;Now start the new sound.
		(self init:)
		(= client (if argc caller else 0))
		(DoSound PlaySound self)
	)


	(method (playMaybe)
		;; Play the sound if no higher priority sound is playing, otherwise
		;; remove it without playing it.  Use this method for sounds which
		;; must be played right now if they are to be played at all, but which
		;; shouldn't interrupt other sounds (e.g., a door opening).

		(self play:&rest)
		(if (== state SND_BLOCKED)
			(self dispose:)
		)
	)


	(method (check)
		;; Private.  Used by the main game loop to determine if an animation
		;; cue or sound completion has occurred.  If so, cue:s the client.

		(if signal
			(if (IsObject client)
				(client cue: self)
			)
			(= prevSignal signal)
			(= signal 0)
		)
	)


	(method (stop doCue)
		;; Stop a sound from playing, but do not dispose: it.

		(if (and argc (not doCue))
			(= client 0)
		)
		(if handle
	 		(DoSound StopSound handle)
		)
	)


	(method (dispose doCue)
		;; Stop a sound, remove it from the sound list, delete its kernel
		;; sound node, and dispose: the sound.

		(if (and argc (not doCue))
			(= client 0)
		)
		(sounds delete: self)
		(if handle
	 		(DoSound KillSound handle)
	 		(= handle 0)
	 	)
		(super dispose:)
	)


	(method (pause value)
		;; A class method, called as (Sound pause: value), where value is
		;; TRUE to pause sounds, FALSE to unpause them.  Returns the previous
		;; value of the pause state.  When an active sound is paused, its
		;; state changes to SND_BLOCKED.

		(return (DoSound PauseSound value))
	)


	(method (changeState)
		;; Change the internal sound node to correspond to the sound object.
		;; Currently only supports changes to the loop property.  Changes to
		;; the priority property will be added at some time.

		(DoSound ChangeSndState self)
	)


	(method (clean who)
		;; Private.  Called from the dispose: method of a Region or Room to
		;; dispose any sounds owned by the Region/Room or which have no owner.

		(if
			(or
				(not owner)
				(== owner who)
			)

			(self dispose:)
		)
	)


	(method (fade doCue)
		;; On systems with volume control, fade the sound away.

		(if (and argc (not doCue))
			(= client 0)
		)
		(DoSound FadeSound handle)
	)
)