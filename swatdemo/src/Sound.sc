;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	SOUND.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1993
;;;;
;;;;	Author: 	Unknown
;;;;	Updated:
;;;;
;;;;	A Sound class is the SCI embodiment of a sound effect, audio, or
;;;;	piece of midi music.
;;;;
;;;;	Classes:
;;;;		Sound


(script#	MUSIC)
(include game.sh)
(use Main)
(use System)


(class Sound kindof Object
	(properties
		nodePtr		0
		handle		0
		flags			0
		number		0
		vol			127	;Do not set directly!
		priority		0		;Do not set	directly!
		loop			1		;Do not set	directly!
		signal		0
		prevSignal	0
		dataInc		0
		min			0
		sec			0
		frame			0
		client		0
		owner			0
	)
;;;	(methods
;;;		play
;;;		stop
;;;		pause
;;;		hold
;;;		release
;;;		fade
;;;		mute
;;;		setVol
;;;		setPri
;;;		setLoop
;;;		send
;;;		check
;;;		clean
;;;
;;;	)
	(method (new who)
		(return
			((super new:)
				owner: (if argc who else 0),
				yourself:
			)
		)
	)
	(method (init)

		;Put yourself on the sounds list, and allocate a
		;sound node in heap with InitSound kernel call, if
		;there isn't already a node allocated

		(= signal 0)
		(= prevSignal 0)
		(sounds add: self)
		(DoSound SndInit self)
	)
	(method (play callerOrVolume caller)

		;Put this objects sound node on the PlayList with PlaySound
		;kernel call.   If the last parameter was an object, set the
		;client property of the sound to be that object.  If there
		;are any MIDI cues in the sound, the object specified in
		;the client property will receive the cue: messages
		;
		;You may no longer pass a volume to play.  8/17/93 - RWL

		;Modified to accept a play volume 17Nov94 -GTP
		;Examples:
		; (mySound play: cueMe) ; will cue cueMe
		; (mySound play: myVol cueMe) ; will set volume and also cue cueMe
		;
		;Here's something to watch out for:
		; ;(mySound play: myVol) ;WRONG  ***This will likely fatal!***
		;Here's the right way: 
		; (mySound play: myVol NULL) ; this is how to set volume but not cue

		(= vol 127)
		(= client NULL)
		(if argc
			(if (== argc 1)
				(= client callerOrVolume)
			else
				(= client caller)
				(= vol (& callerOrVolume $7f) ) ; vol < 128
			)
		)

		(if (not (sounds contains: self))
			(self init:)
		)

		(if (not loop)
			(= loop 1)
		)
;¯gtp¯		(DoSound SndPlay self 0)
		(DoSound SndPlay self)
	)
	(method (stop)

		;Take this objects sound node off the PlayList, and free up its
		;voices and channels for other sounds that may need them

		(if handle
			(DoSound SndUpdateCues self)
	 		(DoSound SndStop self)
		)
	)
	(method (pause value)

		;If value is non-zero, this sound object will be paused, and any
		;voices or channels it owned will be temporarily freed, until it
		;is unpaused by calling this method with a value of 0.  If the
		;Sound class itself receives this method, ALL sounds will be
		;paused.  Normal pause:s may not be nested, however global pause:s
		;are nested.  Therefore, if you have a sound which has been
		;individually pause:d, and then you globally pause everybody by
		;saying (Sound pause:), and then globally unpause everybody, the
		;original paused state of the individual sound will remain intact

		(if (not argc)
			(= value TRUE)
		)
		(DoSound
			SndPause
			(if (and (self isKindOf: Sound) (!= self Sound)) self else 0)
			value
		)	
	)
	(method (hold where)

		;This method only applys to sounds that were set up to be
		;held by the musician.  Holding a sound that was not meant to
		;be held will simply act as an infinite loop on the sound.
		;Musicians can set a hold point in the song, where the programmer
		;may hold at if needed (for syncing with slow machines).  When
		;held the sound will infinitely loop on the same segment set up
		;by the musician until it is released by the programmer.  The
		;'where' argument specifies which hold point to loop at, since
		;there can be multiple points in one song.  Hold:ing at a second
		;or third point will automatically release the previous one.

		(if (not argc)
			(= where 1)
		)
		(DoSound SndHold self where)
	)
	(method (release)

		;Use this method to release a held sound.

		(DoSound SndHold self 0)
	)
	(method (fade newVol fTicks fSteps fEnd whoCares)

		;Fade sound from current volume to newVol.  Fade may go up or
		;down.  A fade rate may be set with fTicks and fSteps.  The sound
		;will wait the amount of 60ths of a second specified in fTicks
		;before making the next volume change.  The size of the volume
		;change is specified in fSteps.  If fEnd is TRUE, the sound will
		;automatically end when the fade is complete.  If fEnd if FALSE,
		;the sound will continue at the new volume.  This is usefull for
		;fading a sound in (from volume 0), in which case you would not
		;want an automatic end when the fade is complete.  Volume ranges
		;from 0 to 127.
		;
		;Note: The parameter list is now fixed.  It's all or nothing. - 8/18/93 RWL

		
		(if argc
			(if (> argc 4)
				(= client whoCares)
			)
			(DoSound SndFade self newVol fTicks fSteps fEnd)
		else
			(DoSound SndFade self 0 25 10 1)
		)
	)
	(method (mute value channel &tmp i theVal)

		;If value is TRUE, the sound will no longer be heard until another call
		;to this method is made where value is FALSE.  The only difference
		;between this method and the pause: method is that by using mute:, the
		;sound will continue to be parsed, so that when you un-mute it, it will
		;come back at the proper place in the sound.  'channel' is an optional
		;parameter that indicates a single channel number to affect.  If channel
		;is not specified, all channels will be affected.

		(if (not argc)
			(= theVal TRUE)
		else
			(= theVal value)
		)

		(if (< argc 2)
			(for	((= i 1))
					(< i 17)
					((++ i))
				(DoSound SndMidiSend self i mCONTROLLER mMUTE theVal)
			)
		else
			(DoSound SndMidiSend self channel mCONTROLLER mMUTE theVal)
		)
	)
	(method (setVol newVol)

		;Change the volume of the sound to newVol (0 - 127).  When
		;changing volume, you should not ever set the vol property
		;directly, but use this method.

		(DoSound SndSetVol self (& newVol $7f) )
	)
	(method (setPri newPri)

		;Set the priority of a sound.  If a value other than -1 is
		;passed, the sound will be fixed at that priority, regardless
		;of the priority information contained in the sound file.
		;If -1 is passed in, the priority will assume a 'floating value',
		;being set by calls to play: from the priority information
		;contained in the sound file.  It is possible for a sound file
		;to have no priority information in it, in which case the
		;current value in priority will be used, whether the priority
		;is fixed or not.

		(DoSound SndSetPri self newPri)
	)
	(method (setLoop newLoop)

		;Change the loop property of a sound.  A value of -1 will
		;cause the sound to loop forever, while every other value
		;will cause only one loop

		(DoSound SndSetLoop self newLoop)
	)
	(method (send channel command value1 value2)

		;This method gives application programmers access to MIDI
		;program changes, benders, and the various controllers.  Any
		;one of these may be sent to any channel of any sound.  The
		;controller argument is number specifying what type of MIDI
		;event you want to send, and they are defined in system.sh.
		;Value may be 0 to 127 for controllers and program changes, and
		;-8192 to 8191 for benders.

		(if (<= 1 channel 15)
			(if (< command 128)
				(DoSound SndMidiSend self channel mCONTROLLER command value1)
			else
				(DoSound	SndMidiSend self channel command value1 value2)
			)
		)
	)
	(method (check)

		;Check for MIDI cues and cue: somebody if needed.

		(if handle
			(DoSound SndUpdateCues self)
		)
		(if signal
			(= prevSignal signal)
			(= signal 0)
			(if client
				(client cue: self)
			)
		)
	)
	(method (clean who)
		(if
			(or
				(not owner)
				(== owner who)
			)
			(self dispose:)
		)
	)
	(method (dispose)

		;Stop the sound, and remove the sound node from heap.

		(sounds delete: self)
		(if nodePtr
	 		(DoSound SndKill self)
	 		(= nodePtr 0)
	 	)

		(super dispose:)
	)
)