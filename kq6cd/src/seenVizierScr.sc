;;; Sierra Script 1.0 - (do not remove this comment)
(script# 221)
(include sci.sh)
(use Main)
(use Kq6Procs)
(use Scaler)
(use DPath)
(use Motion)
(use System)

(public
	seenVizierScr 0
)

(instance egoScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setSpeed: 6
					ignoreActors:
					posn: 75 100
					setPri: 2
					show:
					setMotion: MoveTo 107 94 self
				)
			)
			(1
				(ego
					setPri: -1
					setScale: Scaler 100 94 189 95
					setMotion: DPath 118 107 120 111 123 121 self
				)
			)
			(2 (= cycles 2))
			(3 (ego setHeading: 0 self))
			(4 (self dispose:))
		)
	)
)

(instance seenVizierScr of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 221)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego hide:)
				(Bset 72)
				((ScriptID 220 5) setCycle: EndLoop self)
			)
			(1
				(self setScript: egoScr)
				(= ticks 30)
			)
			(2
				((ScriptID 220 6) init: setMotion: MoveTo 105 94 self)
			)
			(3
				((ScriptID 220 6)
					setPri: -1
					setScale: Scaler 100 94 189 95
					setMotion: DPath 115 107 117 111 118 115 self
				)
			)
			(4
				((ScriptID 220 6) setHeading: 180 self)
			)
			(5
				(if script (-- state))
				(= cycles 2)
			)
			(6 (= cycles 2))
			(7 (messager say: 1 0 5 1 self))
			(8 (messager say: 1 0 5 2 self))
			(9
				((ScriptID 220 6) setMotion: MoveTo 117 111 self)
			)
			(10 (= cycles 2))
			(11
				(messager say: 1 0 5 3 self)
			)
			(12
				((ScriptID 220 6) setMotion: DPath 115 107 105 94 self)
			)
			(13
				((ScriptID 220 6) cue: self)
			)
			(14
				((ScriptID 220 6) dispose:)
				(self setScript: (ScriptID 220 2) self)
			)
			(15
				(messager say: 1 0 5 4 self)
			)
			(16
				(ego reset: 3)
				(theGame handsOn:)
				((ScriptID 220 5) stopUpd:)
				(self dispose:)
			)
		)
	)
)
