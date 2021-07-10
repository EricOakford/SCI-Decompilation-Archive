;;; Sierra Script 1.0 - (do not remove this comment)
(script# 355)
(include game.sh)
(use Main)
(use volleyRm)
(use Intrface)
(use tahiti)
(use Sound)
(use Jump)
(use Motion)
(use System)

(public
	playScript 0
)

(instance playScript of Script
	(properties)
	
	(method (init param1 param2)
		(if argc
			(super init: param1 0 &rest)
			(keyDownHandler addToFront: self)
			(hit1L init:)
			(hit1R init:)
			(hit2L init:)
			(hit2R init:)
		else
			(super init:)
		)
	)
	
	(method (dispose)
		(if modelessDialog (modelessDialog dispose:))
		(keyDownHandler delete: self)
		(hit1L dispose:)
		(hit1R dispose:)
		(hit2L dispose:)
		(hit2R dispose:)
		(super dispose:)
		(DisposeScript 355)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(if (< (register x?) ((ScriptID 3 4) x?))
					(= register
						(if (Random 0 1) (ScriptID 3 1) else (ScriptID 3 6))
					)
					(= temp0 (+ (register nsLeft?) 2))
				else
					(= register
						(if (and (curRoom notify: 1) (Random 0 1))
							ego
						else
							(ScriptID 3 2)
						)
					)
					(= temp0 (- (register nsRight?) 2))
				)
				(if caller
					((ScriptID 3 3) setMotion: JumpTo temp0 170 caller)
					((ScriptID 3 1) heading: 180 view: 603 loop: 2 cel: 0)
					((ScriptID 3 6) heading: 180 view: 803 loop: 2 cel: 4)
					((ScriptID 3 2) heading: 180 view: 203 loop: 2 cel: 4)
					(if (curRoom notify: 1)
						(ego heading: 180 view: 200 setLoop: -1)
						(DirLoop ego (ego heading?))
					)
					(self dispose:)
					(return)
				else
					((ScriptID 3 3)
						setMotion: JumpTo temp0 (+ (register nsTop?) 2) self
					)
					(register setScript: (Clone jumpScript))
				)
				(if (< (register x?) ((ScriptID 3 4) x?))
					(switch (Random 0 1)
						(0 (hit1R play:))
						(1 (hit2R play:))
					)
				else
					(switch (Random 0 1)
						(0 (hit1L play:))
						(1 (hit2L play:))
					)
				)
			)
			(1 (self init:))
		)
	)
	
	(method (handleEvent event &tmp saveCaller)
		(return
			(cond 
				((super handleEvent: event) (return 1))
				((Said 'kick/volleyball') (Print 355 0))
				((or (Said 'play,join') (Said 'hit/volleyball'))
					(if (curRoom notify: 1)
						(TimeHighPrint 4 355 1)
					else
						(if (tahiti volley?)
							(TimeHighPrint 4 355 2)
						else
							(TimeHighPrint 4 355 3)
						)
						(= saveCaller caller)
						(= caller 0)
						(self setScript: (ScriptID 3 5) client)
					)
				)
				((Said 'n') (TimeHighPrint 4 355 4))
				((Said 'look/game,volleyball')
					(if (curRoom notify: 1)
						(TimeHighPrint 4 355 5)
					else
						(TimeHighPrint 4 355 6)
					)
				)
				((Said 'cease,exit')
					(if (curRoom notify: 1)
						(curRoom notify: 1 0)
						(HandsOn)
						(curRoom notify: 4)
						(TimeHighPrint 4 355 7)
					else
						(TimeHighPrint 4 355 8)
					)
				)
			)
		)
	)
)

(instance jumpScript of Script

	(method (doit &tmp temp0)
		(super doit:)
		(= temp0 (ScriptID 3 3))
		(if
			(and
				(== state 0)
				client
				(not (client cycler?))
				(<
					(Abs (- (temp0 x?) ((temp0 mover?) x?)))
					(*
						(temp0 xStep?)
						(+
							3
							(/ (client lastCel:) 2)
							(mod (client lastCel:) 2)
						)
					)
				)
			)
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setCel: -1 cel: 0 setCycle: 0)
			)
			(1 (client setCycle: EndLoop self))
			(2 (self dispose:))
		)
	)
)

(instance hit1L of Sound
	(properties
		number 38
		priority 1
	)
)

(instance hit1R of Sound
	(properties
		number 39
		priority 1
	)
)

(instance hit2L of Sound
	(properties
		number 40
		priority 1
	)
)

(instance hit2R of Sound
	(properties
		number 41
		priority 1
	)
)
