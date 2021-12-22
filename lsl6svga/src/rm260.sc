;;; Sierra Script 1.0 - (do not remove this comment)
(script# 260)
(include sci.sh)
(use Main)
(use fileScr)
(use OccCyc)
(use LarryRoom)
(use Print)
(use Polygon)
(use Feature)
(use Actor)
(use System)

(public
	rm260 0
)

(local
	local0
)
(instance rm260 of LarryRoom
	(properties
		picture 260
		horizon 11
	)
	
	(method (init)
		(super init: &rest)
		(theMusic2 number: 0 stop:)
		(= gLarryRoom curRoom)
		(theIconBar enableIcon: (ScriptID 0 8) show:)
		(self setScript: toTower)
		(star1 init: setCycle: OccCyc self 1 10 65)
		(star2 init: setCycle: OccCyc self 1 10 65)
		(star3 init: setCycle: OccCyc self 1 10 63)
		(star4 init: setCycle: OccCyc self 1 10 65)
		(star5 init: setCycle: OccCyc self 1 10 65)
		(star6 init: setCycle: OccCyc self 1 10 63)
		(star7 init: setCycle: OccCyc self 1 10 65)
		(star8 init: setCycle: OccCyc self 1 10 60)
		(merrButt init:)
	)
	
	(method (cue)
		(if (talkers size:) (messager cue: 1))
		(= local0 1)
		(= gGButtonBarGetCursor theCursor)
		(theGame setCursor: normalCursor)
		(SetCursor 235 105)
		(if
			(Print
				width: 200
				font: userFont
				addTitle: 0 8 0 2 260
				addText: 0 8 0 1 50 1 260
				addIcon: 1911 0 0 0 0
				addButton: 0 3 8 0 1 50 33 260
				addButton: 1 2 8 0 1 150 33 260
				init:
			)
			(ego get: 40 put: 35 put: 20 put: 2)
			(= gLarryRoom 0)
			(theIconBar disableIcon: (ScriptID 0 8))
			(theGame changeScore: 20 174 hideControls: handsOff:)
			(Bclr 8)
			(theMusic stop:)
			(cast eachElementDo: #hide)
			(thePlane drawPic: -1 10)
			(curRoom newRoom: 620)
		else
			(= local0 0)
			(= gLarryRoom curRoom)
			(theIconBar enableIcon: (ScriptID 0 8) show:)
			(theGame setCursor: gGButtonBarGetCursor)
		)
	)
)

(instance toTower of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 3)
			)
			(1 (messager say: 0 0 2 0 self))
			(2 (= ticks 90))
			(3
				(if (and (not local0) (not script))
					(theGame handsOn:)
					(theIconBar disableIcon: (ScriptID 0 3) show:)
				)
				(= seconds 5)
			)
			(4 (curRoom newRoom: 280))
		)
	)
)

(instance buttShot of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 0 1 2 1 self)
			)
			(1
				(curRoom drawPic: 261)
				(= ticks 180)
			)
			(2 (messager say: 0 1 2 2 self))
			(3 (= seconds 5))
			(4 (curRoom newRoom: 280))
		)
	)
)

(instance star1 of Prop
	(properties
		x 54
		y 13
		view 260
		cycleSpeed 8
	)
)

(instance star2 of Prop
	(properties
		x 240
		y 23
		view 260
		loop 1
		cycleSpeed 7
	)
)

(instance star3 of Prop
	(properties
		x 17
		y 17
		view 260
		loop 1
		cel 1
		cycleSpeed 7
	)
)

(instance star4 of Prop
	(properties
		x 295
		y 57
		view 260
		cycleSpeed 10
	)
)

(instance star5 of Prop
	(properties
		x 115
		y 3
		view 260
	)
)

(instance star6 of Prop
	(properties
		x 175
		y 5
		view 260
		loop 1
		cel 3
		cycleSpeed 10
	)
)

(instance star7 of Prop
	(properties
		x 285
		y 16
		view 260
		loop 2
		cycleSpeed 7
	)
)

(instance star8 of Prop
	(properties
		x 64
		y 21
		view 260
		loop 3
		cel 4
		cycleSpeed 10
	)
)

(instance merrButt of Feature
	(properties)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 100 35 129 29 147 42 128 60 117 64 88 61 85 49 88 41
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb)
		(cast eachElementDo: #dispose)
		(curRoom setScript: buttShot)
	)
)
