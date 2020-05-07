;;; Sierra Script 1.0 - (do not remove this comment)
(script# 744)
(include sci.sh)
(use Main)
(use Kq6Procs)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use Motion)
(use System)

(public
	genieCastSpell 0
	saladinKillEgo 1
)

(instance genieCastSpell of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (curRoom obstacles?)
					((curRoom obstacles?) dispose:)
				)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init:
								0
								189
								0
								0
								319
								0
								319
								189
								314
								189
								195
								189
								198
								161
								279
								161
								227
								120
								223
								120
								212
								129
								159
								129
								115
								162
								78
								189
							yourself:
						)
						((Polygon new:)
							type: 2
							init: 165 141 238 141 246 151 159 151
							yourself:
						)
				)
				(= cycles 2)
			)
			(1
				(theIconBar enable:)
				(messager say: 1 0 6 1 self)
			)
			(2
				(self setScript: (ScriptID 742 4) self)
			)
			(3 (messager say: 1 0 6 2 self))
			(4 (messager say: 1 0 6 3 self))
			(5 (messager say: 1 0 6 4 self))
			(6 (messager say: 1 0 6 5 self))
			(7 (messager say: 1 0 6 6 self))
			(8
				(LoadMany 0 1004 1063 1029 1001 1026)
				(UnLoad 128 892)
				(UnLoad 128 899)
				(UnLoad 128 8921)
				(UnLoad 128 8992)
				(UnLoad 128 891)
				(UnLoad 128 890)
				(self setScript: (ScriptID 740 23) self)
			)
			(9
				(ego put: 24)
				(theGame handsOn:)
				((ScriptID 740 3) setScript: (ScriptID 742 5))
				(self dispose:)
				(DisposeScript 744)
			)
		)
	)
)

(instance saladinKillEgo of Script
	(properties)
	
	(method (dispose)
		(super dispose: &rest)
		(EgoDead 33)
		(DisposeScript 744)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(theIconBar enable:)
				(if (Btst 156)
					(= register 999)
					((ScriptID 740 2) view: 7415 setLoop: 0 cel: 3 setPri: 9)
				else
					(= state 11)
				)
				(= cycles 2)
			)
			(2
				(Bset 59)
				(messager say: 1 0 38 1 self)
			)
			(3
				(Bclr 59)
				(messager say: 1 0 38 2 self)
			)
			(4
				((ScriptID 740 2) setCycle: CT 5 1 self)
			)
			(5 (= ticks 90))
			(6
				((ScriptID 740 2) setCycle: CT 10 1 self)
			)
			(7
				(Bset 59)
				(messager say: 1 0 38 3 self)
			)
			(8
				(Bclr 59)
				(messager say: 1 0 38 4 self)
			)
			(9
				(Bset 59)
				(messager say: 1 0 38 5 self)
			)
			(10
				((ScriptID 740 2) setCycle: End self)
			)
			(11
				(Bclr 59)
				(messager say: 1 0 38 6 self)
			)
			(12
				(theMusic number: 0 stop:)
				(theMusic number: 705 setLoop: 1 play:)
				((ScriptID 740 5) view: 736 cycleSpeed: 3 moveSpeed: 3)
				(Face (ScriptID 740 5) ego self)
			)
			(13
				(if (not register)
					(messager say: 1 0 5 0 self)
				else
					(= cycles 1)
				)
			)
			(14
				(ego setHeading: 180)
				((ScriptID 740 5)
					setMotion: PolyPath (- (ego x?) 24) (+ (ego y?) 5) self
				)
			)
			(15
				((ScriptID 740 5) setHeading: 0 self)
			)
			(16
				(ego hide:)
				((ScriptID 740 5)
					view: 738
					setLoop: 2
					cel: 0
					cycleSpeed: 8
					setCycle: CT 2 1 self
				)
				(theGlobalSound number: 0 stop:)
				(theGlobalSound number: 756 setLoop: 1 play:)
			)
			(17
				(theGlobalSound number: 0 stop:)
				(theGlobalSound number: 971 setLoop: 1 play: self)
			)
			(18
				(theGlobalSound number: 0 stop:)
				(theGlobalSound number: 652 setLoop: 1 play:)
				((ScriptID 740 5) setCycle: End self)
			)
			(19
				(switch register
					(29
						(messager say: 1 0 29 0 self)
					)
					(else  (= cycles 2))
				)
			)
			(20 (self dispose:))
		)
	)
)
