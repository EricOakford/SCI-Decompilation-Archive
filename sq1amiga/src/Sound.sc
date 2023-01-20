;;; Sierra Script 1.0 - (do not remove this comment)
;*********************************************************************
;*
;* SOUND.SC
;*
;*********************************************************************

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
;;;		;The following two methods will remain
;;;		;in the sound class only while KQ5 cd and
;;;		;Jones cd remain unshipped (for
;;;		;compatability's sake).  Everyone else
;;;		;should not be using these methods.
;;;
;;;		playBed
;;;		changeState
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
		(DoSound InitSound self)
	)
	(method (play newVol &tmp argCount)

		;Put this objects sound node on the PlayList with PlaySound
		;kernel call.  If no value was passed in newVol, set the
		;volume to maximum (127), else set the volume to the value
		;in newVol.  If the last parameter was an object, set the
		;client property of the sound to be that object.  If there
		;are any MIDI cues in the sound, the object specified in
		;the client property will receive the cue: messages

		(= argCount argc)
		(if
			(and
				argc
				(IsObject [newVol (- argc 1)])
			)
			(= client [newVol (= argCount (- argc 1))])
		else
			(= client 0)
		)

		(self init:)

		(if (not loop)
			(= loop 1)
		)

		(if argCount
			(= vol newVol)
		else
			(= vol 127)
		)

		(DoSound PlaySound self 0)
	)
	(method (stop)

		;Take this objects sound node off the PlayList, and free up its
		;voices and channels for other sounds that may need them

		(if handle
			(DoSound UpdateCues self)
	 		(DoSound StopSound self)
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
			PauseSound 
			(if (self isMemberOf: Sound) self else 0)
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
		(DoSound HoldSound self where)
	)
	(method (release)

		;Use this method to release a held sound.

		(DoSound HoldSound self 0)
	)
	(method (fade newVol fTicks fSteps fEnd &tmp argCount tmpNewVol)

		;Fade sound from current volume to newVol.  Fade may go up or
		;down.  A fade rate may be set with fTicks and fSteps.  The sound
		;will wait the amount of 60ths of a second specified in fTicks
		;before making the next volume change.  The size of the volume
		;change is specified in fSteps.  If fEnd is TRUE, the sound will
		;automatically end when the fade is complete.  If fEnd if FALSE,
		;the sound will continue at the new volume.  This is usefull for
		;fading a sound in (from volume 0), in which case you would not
		;want an automatic end when the fade is complete.  Volume ranges
		;from 0 to 127.  If only a newVol paramater is specified, a
		;default rate will be assumed, and fEnd will be TRUE if newVol
		;is 0.  If no paramaters are specified, newVol will default to
		;zero.
		
		(= argCount argc)
		(if
			(and
				argc
				(IsObject [newVol (- argc 1)])
			)
			(= client [newVol (= argCount (- argc 1))])
		)

		(if (not argCount)
			(= tmpNewVol 0)
			(= argCount 1)
		else
			(= tmpNewVol newVol)
		)

		(if (> argCount 1)
			(DoSound FadeSound self tmpNewVol fTicks fSteps fEnd)
		else
			(DoSound FadeSound self tmpNewVol 25 10 (not tmpNewVol))
		)
	)
	(method (mute value)

		;If value is TRUE, the sound will no longer be heard until
		;another call to this method is made where value is FALSE.
		;The only difference between this method and the pause:
		;method is that by using mute:, the sound will continue to
		;be parsed, so that when you un-mute it, it will come back
		;at the proper place in the sound.

		(if (not argc)
			(= value TRUE)
		)

		(DoSound MuteSound self value)
	)
	(method (setVol newVol)

		;Change the volume of the sound to newVol (0 - 127).  When
		;changing volume, you should not ever set the vol property
		;directly, but use this method.

		(DoSound SetVol self newVol)
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

		(DoSound SetPri self newPri)
	)
	(method (setLoop newLoop)

		;Change the loop property of a sound.  A value of -1 will
		;cause the sound to loop forever, while every other value
		;will cause only one loop

		(DoSound SetLoop self newLoop)
	)
	(method (send channel command value1 value2)

		;This method gives application programmers access to MIDI
		;program changes, benders, and the various controllers.  Any
		;one of these may be sent to any channel of any sound.  The
		;controller argument is number specifying what type of MIDI
		;event you want to send, and they are defined in system.sh.
		;Value may be 0 to 127 for controllers and program changes, and
		;0 to 16383 for benders.

		(if (<= 1 channel 15)
			(if (< command 128)
				(DoSound MidiSend self channel mCONTROLLER command value1)
			else
				(DoSound	MidiSend self channel command value1 value2)
			)
		)
	)
	(method (check)

		;Check for MIDI cues and cue: somebody if needed.

		(if handle
			(DoSound UpdateCues self)
		)
		(if signal
			(= prevSignal signal)
			(= signal 0)
			(if (IsObject client)
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
	 		(DoSound KillSound self)
	 		(= nodePtr 0)
	 	)

		(super dispose:)
	)


	;; The following methods WILL be removed after KQ5 cd and
	;; Jones cd are shipped.  There is no longer any reason for
	;; any other application code to be using these methods.

	(method (playBed newVol &tmp argCount)
		(= argCount argc)
		(if
			(and
				argc
				(IsObject [newVol (- argc 1)])
			)
			(= client [newVol (= argCount (- argc 1))])
		else
			(= client 0)
		)

		(self init:)

		(if (not loop)
			(= loop 1)
		)

		(if argCount
			(= vol newVol)
		else
			(= vol 127)
		)

		(DoSound PlaySound self 1)
	)
	(method (changeState)
		(DoSound ChangeSndState self)
	)
)
