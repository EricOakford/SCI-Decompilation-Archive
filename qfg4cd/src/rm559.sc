;;; Sierra Script 1.0 - (do not remove this comment)
(script# 559)
(include sci.sh)
(use Main)
(use GloryRm)
(use ForestView)
(use PChase)
(use PolyPath)
(use Polygon)

(public
	rm559 0
)

(instance rm559 of GloryRm
	(properties
		picture 420
		horizon 70
		north 558
		east 565
		south 560
		west 554
		topX 164
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init: 0 0 146 0 146 73 139 97 130 104 32 91 32 70 0 70
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 319 85 243 86 183 73 183 0 319 0
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 319 134 213 134 213 102 319 102
					yourself:
				)
		)
		(self setRegions: 50)
		(if (Btst 35)
			(self setRegions: 51)
			(= east (= south 0))
			((ScriptID 51 1) init: posn: 300 100 setHeading: 270)
			((ScriptID 51 2) init: posn: 160 170 setHeading: 315)
		)
		((ScriptID 50 1) caller: self)
		(super init: &rest)
		(curRoom
			addPoly:
				((Polygon new:)
					init: 0 122 50 137 79 109 205 123 293 136 314 162 275 164 277 189 0 189
					yourself:
				)
				60
		)
		(atp1 init:)
		(atp2 init: setPri: 127)
		(atp3 init:)
		(if Night
			(atp4 init: setPri: 249)
			(atp5 init: setPri: 249)
		)
		(if (Btst 380) (theGame save: 1))
	)
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				(Btst 35)
				(< (ego x?) 7)
				(not (cast contains: (ScriptID 51 3)))
				(!= (curRoom script?) (ScriptID 50 1))
			)
			((ScriptID 51 3)
				posn: (- (- (ego x?) 25) 15) (ego y?)
				init:
				setMotion: PChase ego 25
			)
		)
		(if
			(and
				(Btst 35)
				(> (ego y?) 160)
				(not ((ScriptID 51 2) mover?))
			)
			((ScriptID 51 2) setMotion: PChase ego 25)
		)
		(if
			(and
				(Btst 35)
				(> (ego x?) 300)
				(not ((ScriptID 51 1) mover?))
			)
			((ScriptID 51 1) setMotion: PChase ego 25)
		)
	)
	
	(method (cue)
		(if (== prevRoomNum (curRoom west?))
			(ego setMotion: PolyPath (+ (ego x?) 15) (ego y?))
		)
	)
	
	(method (notify param1)
		(if
			(and
				(== param1 1)
				(!= (curRoom script?) (ScriptID 51 4))
			)
			(curRoom setScript: (ScriptID 51 4))
		)
	)
)

(instance atp1 of ForestView
	(properties
		x 40
		y 14
		view 424
		loop 2
		cel 4
	)
)

(instance atp2 of ForestView
	(properties
		x 223
		y 98
		view 416
		cel 4
	)
)

(instance atp3 of ForestView
	(properties
		x 117
		y 73
		view 416
		cel 3
	)
)

(instance atp4 of ForestView
	(properties
		x 19
		y 73
		view 428
	)
)

(instance atp5 of ForestView
	(properties
		x 26
		y 189
		view 428
		cel 1
	)
)
