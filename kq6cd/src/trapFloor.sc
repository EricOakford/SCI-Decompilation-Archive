;;; Sierra Script 1.0 - (do not remove this comment)
(script# 435)
(include sci.sh)
(use Main)
(use rLab)
(use n401)
(use Kq6Procs)
(use PolyPath)
(use Motion)
(use Actor)
(use System)

(public
	trapFloor 0
)

(instance trapFloor of LabRoom
	(properties
		north 400
		west 400
	)
	
	(method (init)
		(proc401_2)
		(super init: &rest)
		((ScriptID 30 0) initCrypt: 2)
		((ScriptID 30 5) addToPic:)
		((ScriptID 30 9) addToPic:)
		((ScriptID 30 8) addToPic:)
		(self setScript: fallTrapFloor)
	)
)

(instance fallTrapFloor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theIconBar disable: 6)
				(ego posn: 36 164 init: setMotion: PolyPath 120 164 self)
			)
			(1 (= cycles 8))
			(2
				(westHalfTrapFloor init: stopUpd:)
				(eastHalfTrapFloor init: stopUpd:)
				(eastTrapLedge init: stopUpd:)
				(westTrapLedge init: stopUpd:)
				(northTrapLedge init: stopUpd:)
				(= cycles 2)
			)
			(3
				(theMusic stop:)
				(theGlobalSound number: 909 setLoop: 1 play: self)
			)
			(4
				(theMusic number: 409 setLoop: -1 play:)
				(= cycles 4)
			)
			(5
				(if (Btst 1)
					(messager say: 1 0 30 0 self 400)
				else
					(messager say: 1 0 29 1 self 400)
				)
			)
			(6
				(ego
					setLoop: (ego cel?)
					normal: 0
					cycleSpeed: 1
					setCycle: Forward
				)
				(= seconds 3)
			)
			(7
				(theMusic stop:)
				(theGlobalSound number: 404 setLoop: 1 play:)
				(ego
					setCycle: 0
					setStep: 40 30
					setMotion: MoveTo (ego x?) 250 self
				)
			)
			(8
				(theIconBar enable: 6)
				(curRoom style: -32758 newRoom: 406)
			)
		)
	)
)

(instance westHalfTrapFloor of View
	(properties
		x 83
		y 159
		view 409
		priority 1
		signal $4010
	)
)

(instance eastHalfTrapFloor of View
	(properties
		x 233
		y 158
		view 409
		loop 1
		priority 1
		signal $4010
	)
)

(instance northTrapLedge of View
	(properties
		x 108
		y 148
		view 409
		loop 4
		priority 2
		signal $4010
	)
)

(instance eastTrapLedge of View
	(properties
		x 211
		y 141
		view 409
		loop 2
		priority 2
		signal $4010
	)
)

(instance westTrapLedge of View
	(properties
		x 104
		y 140
		view 409
		loop 3
		priority 2
		signal $4010
	)
)
