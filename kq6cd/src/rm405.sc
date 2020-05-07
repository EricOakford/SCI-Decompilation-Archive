;;; Sierra Script 1.0 - (do not remove this comment)
(script# 405)
(include sci.sh)
(use Main)
(use rLab)
(use n402)
(use Kq6Procs)
(use Conv)
(use Scaler)
(use Polygon)
(use Motion)
(use Actor)
(use System)

(public
	rm405 0
)

(instance myConv of Conversation
	(properties)
)

(instance rm405 of LabRoom
	(properties
		north 400
		west 400
	)
	
	(method (init)
		(if (Btst 1)
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 2
						init: 149 148 102 148 83 142 83 152 74 160 0 160 0 0 149 0
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 172 148 171 0 319 0 319 189 180 189 180 185 294 185 231 142 211 148
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 64 168 54 176 17 185 140 185 140 189 0 189 0 168
						yourself:
					)
			)
		else
			(proc402_2)
		)
		((ScriptID 30 0) labCoords: 117)
		(super init: &rest)
		(if (and (not (Btst 1)) (!= prevRoomNum 400))
			(self setScript: closeEntranceDoor)
		else
			(if (!= prevRoomNum 400)
				((ScriptID 30 0) prevEdgeHit: 1)
			)
			(curRoom setScript: (ScriptID 30 1))
		)
		((ScriptID 30 0) initCrypt: 2)
		((ScriptID 30 7) addToPic:)
		((ScriptID 30 5) addToPic:)
		((ScriptID 30 9) addToPic:)
		(door addToPic:)
		(lBlock addToPic:)
		(rBlock addToPic:)
	)
	
	(method (doit)
		(cond 
			((curRoom script?))
			((== (ego edgeHit?) 3)
				((ScriptID 30 0) prevEdgeHit: 3)
				(curRoom setScript: walkOut)
			)
		)
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (Btst 1)
					(messager say: 2 1 14 1 0 400)
				else
					(messager say: 2 1 15 1 0 400)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance door of Prop
	(properties
		y 190
		noun 11
		modNum 400
		sightAngle 45
		approachX 160
		approachY 178
		view 400
		priority 15
		signal $4010
	)
	
	(method (init)
		(self approachVerbs: 5)
		(if (Btst 1) (self x: 259) else (self x: 160))
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(if (not (Btst 1))
					(messager say: 11 5 15 1 0 400)
				else
					(messager say: 11 5 14 1 0 400)
				)
			)
			(1
				(if (Btst 1)
					(messager say: 11 1 14 1 0 400)
				else
					(messager say: 11 1 15 1 0 400)
				)
			)
			(2
				(super doVerb: theVerb &rest)
			)
			(else 
				(if (Btst 1)
					(messager say: 11 0 14 1 0 400)
				else
					(messager say: 11 0 15 1 0 400)
				)
			)
		)
	)
)

(instance lBlock of View
	(properties
		x 123
		y 189
		noun 6
		modNum 400
		view 408
		loop 1
		priority 15
		signal $5010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(myConv add: 400 6 1 9 1 add: 400 6 1 9 2 init:)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance rBlock of View
	(properties
		x 273
		y 189
		noun 6
		modNum 400
		view 408
		loop 2
		priority 15
		signal $4010
	)
	
	(method (doVerb theVerb)
		(lBlock doVerb: theVerb)
	)
)

(instance walkOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo (ego x?) 250 self)
			)
			(1 (curRoom newRoom: 340))
		)
	)
)

(instance closeEntranceDoor of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 3])
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					posn: 158 250
					normal: 1
					init:
					reset:
					setScale: Scaler 100 99 190 0
					ignoreHorizon:
					setMotion: MoveTo 160 165 self
				)
			)
			(1 (= cycles 2))
			(2
				(soundFx2 number: 434 setLoop: 1 play: self)
			)
			(3
				(if (not (Btst 1))
					(messager say: 1 0 11 0 self 400)
				else
					(self cue:)
				)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)
