;;; Sierra Script 1.0 - (do not remove this comment)
(script# MUSIC)
(include game.sh)
(use Main)
(use System)


(class Sound of Object
	(properties
		nodePtr 0
		handle 0
		number 0
		vol 127
		priority 0
		loop 1
		signal 0
		prevSignal 0
		dataInc 0
		min 0
		sec 0
		frame 0
		client 0
		owner 0
	)
	
	(method (new who)
		((super new:)
			owner: (if argc who else 0)
			yourself:
		)
	)
	
	(method (init)
		(= signal 0)
		(= prevSignal 0)
		(sounds add: self)
		(DoSound InitSound self)
	)
	
	(method (dispose doCue)
		(if (and argc (not doCue))
			(= client 0)
		)
		(sounds delete: self)
		(if nodePtr
			(DoSound KillSound self)
			(= nodePtr 0)
		)
		(super dispose:)
	)
	
	(method (play caller)
		(self init:)
		(= client (if argc caller else 0))
		(if (not loop)
			(= loop 1)
		)
		(DoSound PlaySound self 0)
	)
	
	(method (playBed caller)
		(self init:)
		(= client (if argc caller else 0))
		(if (not loop)
			(= loop 1)
		)
		(DoSound PlaySound self 1)
	)
	
	(method (stop doCue)
		(if (and argc (not doCue))
			(= client 0)
		)
		(if nodePtr
			(DoSound StopSound self)
		)
	)
	
	(method (pause value)
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
		(if (not argc)
			(= where 1)
		)
		(DoSound HoldSound self where)
	)
	
	(method (release)
		(DoSound HoldSound self 0)
	)
	
	(method (fade newVol fTicks fSteps fEnd doCue)
		(if (and (> argc 4) doCue)
			(= client 0)
		)
		(if argc
			(DoSound FadeSound self newVol fTicks fSteps fEnd)
		else
			(DoSound FadeSound self 0 25 10 1)
		)
	)
	
	(method (send channel command value)
		(if (and (<= 1 channel) (<= channel 15))
			(DoSound MidiSend self channel command value)
		)
	)
	
	(method (changeState)
		(DoSound ChangeSndState self)
	)
	
	(method (check)
		(DoSound UpdateCues self)
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
)
