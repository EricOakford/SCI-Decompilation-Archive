;;; Sierra Script 1.0 - (do not remove this comment)
(script# 222)
(include sci.sh)
(use Main)
(use Kq6Procs)
(use Scaler)
(use Motion)
(use System)

(public
	giveRingScr 0
)

(instance giveRingScr of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 222)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theGame givePoints: 3)
				(= cycles 1)
			)
			(1
				(messager say: 5 70 register 1 self)
			)
			(2
				(ego
					setSpeed: 6
					normal: 0
					view: 221
					loop: 0
					cel: 0
					setCycle: End self
				)
			)
			(3 (= cycles 2))
			(4
				(messager say: 5 70 register 2 self)
			)
			(5
				((ScriptID 220 3) setCycle: Beg self)
			)
			(6
				((ScriptID 220 3)
					view: 725
					setCycle: Walk
					setMotion: MoveTo 99 119 self
				)
			)
			(7
				((ScriptID 220 3) setHeading: 90 self)
			)
			(8 (= cycles 2))
			(9
				(messager say: 5 70 register 3 self)
			)
			(10 (ego setCycle: Beg self))
			(11
				(messager say: 5 70 register 4 self)
			)
			(12
				(if (== register 14)
					(messager say: 5 70 register 5 self)
				else
					(self cue:)
				)
			)
			(13
				(ego reset: 7)
				((ScriptID 220 4) setCycle: Beg)
				((ScriptID 220 3) setHeading: 0 self)
			)
			(14
				(ego loop: 3)
				(self setScript: (ScriptID 220 1) self 0)
			)
			(15 (= seconds 4))
			(16 (script cue:))
			(17
				((ScriptID 220 6) init: setMotion: MoveTo 105 94 self)
			)
			(18
				((ScriptID 220 6)
					setPri: -1
					setScale: Scaler 100 94 189 95
					setMotion: MoveTo 117 111 self
				)
			)
			(19
				(if (not (== ((ScriptID 220 6) loop?) 2))
					((ScriptID 220 6) setHeading: 0 self)
				else
					(self cue:)
				)
			)
			(20 (= cycles 2))
			(21
				(messager say: 5 70 14 6 self)
			)
			(22
				(messager say: 5 70 14 7 self)
			)
			(23
				(messager say: 5 70 14 8 self)
			)
			(24
				((ScriptID 220 6) setMotion: MoveTo 109 93 self)
			)
			(25
				((ScriptID 220 6) cue:)
				(ego
					setSpeed: 10
					ignoreActors:
					setMotion: MoveTo 107 93 self
				)
			)
			(26
				(ego
					setPri: 2
					setScale: Scaler 64 94 103 95
					moveSpeed: 8
					setMotion: MoveTo 75 100 self
				)
			)
			(27
				(self setScript: (ScriptID 220 2) self)
			)
			(28
				(theGame handsOn:)
				(Bset 72)
				(curRoom newRoom: 150)
			)
		)
	)
)
