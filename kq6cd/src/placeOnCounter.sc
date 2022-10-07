;;; Sierra Script 1.0 - (do not remove this comment)
(script# 287)
(include sci.sh)
(use Main)
(use Motion)
(use System)

(public
	placeOnCounter 0
	getFromCounter 1
)

(instance placeOnCounter of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 287)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					put:
					(switch register
						(0 48)
						(3 27)
						(1 3)
						(2 14)
					) curRoomNum
				)
				(self setScript: (ScriptID 286 2) 0 register)
			)
			(1 (client cue:))
			(2
				((ScriptID 280 2)
					posn: 236 116
					view: 284
					loop: 3
					cel: 0
					cycleSpeed: 9
				)
				(= ticks 6)
			)
			(3
				((ScriptID 280 2) setCycle: CycleTo 1 1 self)
			)
			(4 (= ticks 6))
			(5
				(script cue:)
				((ScriptID 280 2)
					setPri: 14
					view: (if (OneOf register 3 2) 2842 else 2843)
					loop:
					(switch register
						(3 0)
						(2 1)
						(0 0)
						(1 1)
					)
					cel: 0
				)
				(= ticks 6)
			)
			(6
				((ScriptID 280 2) setCycle: CycleTo 3 1 self)
			)
			(7
				(switch register
					(0 ((ScriptID 280 5) init:))
					(2 ((ScriptID 280 6) init:))
					(3 ((ScriptID 280 4) init:))
					(1 ((ScriptID 280 7) init:))
				)
				(= cycles 2)
			)
			(8
				(if
					(!=
						((ScriptID 280 2) lastCel:)
						((ScriptID 280 2) cel?)
					)
					((ScriptID 280 2) setCycle: EndLoop self)
				else
					(= cycles 2)
				)
			)
			(9 (= ticks 6))
			(10
				((ScriptID 280 2)
					setPri: -1
					posn: 236 127
					view: 280
					loop: 8
					cel: 0
					cycleSpeed: 6
				)
				(= cycles 2)
			)
			(11 (self dispose:))
		)
	)
)

(instance getFromCounter of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 287)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 280 2)
					view: (if (OneOf register 3 2) 2842 else 2843)
					posn: 236 116
					setPri: 14
					loop:
					(switch register
						(3 0)
						(2 1)
						(0 0)
						(1 1)
					)
					cel: 2
				)
				(ego
					get:
					(switch register
						(0 48)
						(3 27)
						(1 3)
						(2 14)
					)
				)
				(switch register
					(0 ((ScriptID 280 5) dispose:))
					(2 ((ScriptID 280 6) dispose:))
					(3 ((ScriptID 280 4) dispose:))
					(1 ((ScriptID 280 7) dispose:))
				)
				(= cycles 3)
			)
			(1
				((ScriptID 280 2) setCycle: BegLoop self)
			)
			(2 (= cycles 2))
			(3
				(self setScript: (ScriptID 286 2) self)
			)
			(4
				(script register: register cue:)
				((ScriptID 280 2)
					view: 284
					setPri: -1
					loop: 3
					cel: 1
					setCycle: BegLoop self
				)
			)
			(5 0)
			(6 (= cycles 2))
			(7
				((ScriptID 280 2) posn: 236 127 view: 280 loop: 8 cel: 0)
				(= cycles 2)
			)
			(8 (self dispose:))
		)
	)
)
