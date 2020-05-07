;;; Sierra Script 1.0 - (do not remove this comment)
(script# 482)
(include sci.sh)
(use Main)
(use PolyPath)
(use Motion)
(use Actor)
(use System)

(public
	proc482_0 0
	proc482_1 1
	proc482_2 2
)

(local
	local0
)
(procedure (proc482_0)
	((ScriptID 480 5) register: 1)
	(ego setScript: grabForHole)
)

(procedure (proc482_1)
	(ego setScript: getHole)
)

(procedure (proc482_2)
	((ScriptID 480 5) register: 1)
	(ego setScript: lookThruHole)
)

(instance getHole of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theIconBar disable: 6)
				(= seconds 2)
			)
			(1
				(if (> ((ScriptID 480 1) x?) 280)
					(soundFx2 number: 483 setLoop: 1 play:)
					((ScriptID 480 1)
						setLoop: 5
						setCycle: Walk
						setMotion: MoveTo 263 47
					)
					(= local0 1)
				)
				(ego setMotion: PolyPath 302 92 self)
			)
			(2
				(ego setMotion: PolyPath 273 87 self)
			)
			(3
				(if (== local0 1)
					(self cue:)
				else
					(soundFx2 number: 483 setLoop: 1 play:)
					((ScriptID 480 1)
						setLoop: 5
						setCycle: Walk
						setMotion: MoveTo 256 64 self
					)
				)
			)
			(4
				(if (== local0 1)
					(= local0 0)
					((ScriptID 480 1)
						setLoop: 4
						cel: 5
						posn: 254 50
						cycleSpeed: 3
						setCycle: Beg
					)
				)
				(ego
					normal: 0
					view: 482
					setLoop: 3
					cel: 0
					posn: 258 90
					setCycle: CT 3 1 self
				)
			)
			(5
				(theGame givePoints: 1)
				(soundFx2 stop:)
				((ScriptID 480 1) dispose:)
				(ego get: 18 setCycle: End self)
			)
			(6
				(ego posn: 273 87 reset: 7)
				(= cycles 6)
			)
			(7
				(messager say: 21 5 10 1 self 480)
			)
			(8
				(ego setMotion: PolyPath 197 116 self)
			)
			(9 (ego setHeading: 135 self))
			(10 (= cycles 2))
			(11
				((ScriptID 480 5) register: 1)
				(theIconBar enable: 6)
				(theGame handsOn:)
				(self dispose:)
				(DisposeScript 482)
			)
		)
	)
)

(instance grabForHole of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 21 5 8 1 self 480)
			)
			(1
				(ego setMotion: PolyPath 300 97 self)
			)
			(2
				(ego setMotion: PolyPath 294 81 self)
			)
			(3
				(messager say: 21 5 8 2 self 480)
			)
			(4
				((ScriptID 480 1) hide:)
				(soundFx2 number: 483 setLoop: 1 play:)
				(ego
					view: 482
					setLoop: 0
					cel: 0
					normal: 0
					setPri: 5
					posn: 280 83
					cycleSpeed: 8
					setCycle: End self
				)
			)
			(5
				((ScriptID 480 1) posn: 238 70 show:)
				(ego posn: 294 81 reset: 7)
				(= ticks 6)
			)
			(6
				(messager say: 21 5 8 3 self 480)
			)
			(7
				(ego setMotion: PolyPath 197 116 self)
			)
			(8
				(ego setHeading: 135)
				(theGame handsOn:)
				(self dispose:)
				(DisposeScript 482)
			)
		)
	)
)

(instance lookThruHole of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if ((ScriptID 40 0) holeLooks?)
					(self cue:)
				else
					(messager say: 21 1 5 1 self 480)
				)
			)
			(1
				(ego setMotion: PolyPath 283 94 self)
			)
			(2
				(ego setHeading: 0)
				(= ticks 6)
			)
			(3
				(if ((ScriptID 40 0) holeLooks?)
					(messager say: 21 1 6 1 self 480)
				else
					(self cue:)
				)
			)
			(4
				(holeInset init:)
				(= seconds 3)
			)
			(5
				(holeInset dispose:)
				(if ((ScriptID 40 0) holeLooks?)
					(messager say: 21 1 6 2 self 480)
				else
					(messager say: 21 1 5 2 self 480)
				)
			)
			(6
				(ego
					setLoop: 3
					setMotion: MoveTo (+ (ego x?) 5) (+ (ego y?) 5) self
				)
			)
			(7
				(if ((ScriptID 40 0) holeLooks?)
					(self cue:)
				else
					(soundFx2 number: 483 setLoop: 1 play:)
					((ScriptID 480 1)
						setMotion:
							MoveTo
							(- ((ScriptID 480 1) x?) 5)
							((ScriptID 480 1) y?)
							self
					)
				)
			)
			(8
				(if ((ScriptID 40 0) holeLooks?)
					(self cue:)
				else
					((ScriptID 480 1)
						setMotion:
							MoveTo
							(+ ((ScriptID 480 1) x?) 5)
							((ScriptID 480 1) y?)
							self
					)
				)
			)
			(9
				(if ((ScriptID 40 0) holeLooks?)
					(self cue:)
				else
					(messager say: 21 1 5 3 self 480)
				)
			)
			(10
				(theGame handsOn:)
				(ego reset: 3)
				((ScriptID 40 0) holeLooks: 1)
				(self dispose:)
				(DisposeScript 482)
			)
		)
	)
)

(instance holeInset of View
	(properties
		x 162
		y 98
		view 487
		priority 15
		signal $4010
	)
	
	(method (init)
		(fields init:)
		(super init:)
	)
	
	(method (dispose)
		(fields dispose:)
		(super dispose:)
	)
)

(instance fields of View
	(properties
		x 164
		y 93
		view 490
		priority 14
		signal $4010
	)
)
