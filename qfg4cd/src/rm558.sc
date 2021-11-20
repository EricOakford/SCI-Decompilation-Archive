;;; Sierra Script 1.0 - (do not remove this comment)
(script# 558)
(include sci.sh)
(use Main)
(use GloryRm)
(use ForestView)
(use PChase)
(use PolyPath)
(use Polygon)

(public
	rm558 0
)

(local
	curRoomWest
)
(instance rm558 of GloryRm
	(properties
		picture 410
		style $0400
		horizon 70
		north 557
		south 559
		west 553
		topX 141
		leftY 135
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						75
						71
						108
						86
						130
						86
						153
						94
						176
						116
						121
						116
						87
						127
						0
						127
						0
						0
						133
						0
						133
						71
					yourself:
				)
				((Polygon new:)
					type: 2
					init:
						619
						0
						619
						189
						219
						189
						237
						173
						274
						159
						252
						159
						252
						145
						300
						145
						300
						124
						252
						132
						208
						132
						208
						124
						216
						119
						216
						105
						164
						93
						164
						85
						194
						73
						194
						0
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 0 143 108 143 108 189 0 189
					yourself:
				)
		)
		(self setRegions: 50)
		(if (Btst 35)
			(self setRegions: 51)
			((ScriptID 50 1) caller: self)
		)
		(super init: &rest)
		(curRoom
			addPoly:
				((Polygon new:)
					init: 253 125 185 137 157 125 230 111
					yourself:
				)
				60
				((Polygon new:)
					init: 136 189 223 156 242 178 229 189
					yourself:
				)
				130
				((Polygon new:)
					init: 118 158 155 119 147 150 204 140 230 150 172 153 167 169
					yourself:
				)
				130
		)
		(atp1 init: setPri: 159)
		(atp2 init:)
		(atp3 init: setPri: 106)
		(atp4 init: setPri: 169)
		(if Night (atp5 init:) (atp6 init: setPri: 169))
		(if (Btst 380) (theGame save: 1))
	)
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				(Btst 35)
				(not (cast contains: (ScriptID 51 1)))
				(!= (curRoom script?) (ScriptID 50 1))
			)
			(if (< (ego x?) 7)
				(= curRoomWest (curRoom west?))
				((ScriptID 51 1)
					posn: (- (- (ego x?) 25) 15) (ego y?)
					init:
					setMotion: PChase ego 25
				)
			)
			(if
				(and
					(> (ego y?) 182)
					(== prevRoomNum (curRoom south?))
				)
				(= curRoomWest (curRoom south?))
				((ScriptID 51 1)
					posn: (ego x?) (+ (ego y?) 25 15)
					init:
					setMotion: PChase ego 25
				)
			)
		)
	)
	
	(method (cue)
		(if (== prevRoomNum (curRoom west?))
			(ego setMotion: PolyPath (+ (ego x?) 15) (ego y?))
		else
			(ego setMotion: PolyPath (ego x?) (- (ego y?) 10))
		)
	)
	
	(method (notify param1)
		(if (== param1 1)
			((ScriptID 51 2)
				init:
				posn: topX (- horizon 10)
				setHeading: 180
			)
			(cond 
				((== curRoomWest (curRoom west?)) ((ScriptID 51 3) init: posn: 160 210 setHeading: 0))
				((== curRoomWest (curRoom south?)) ((ScriptID 51 3) init: posn: -20 100 setHeading: 90))
			)
			(curRoom setScript: (ScriptID 51 4))
		)
	)
)

(instance atp1 of ForestView
	(properties
		x 319
		y 108
		view 413
		loop 1
	)
)

(instance atp2 of ForestView
	(properties
		x 225
		y 49
		view 413
		loop 1
		cel 1
	)
)

(instance atp3 of ForestView
	(properties
		x 68
		y 7
		view 414
		loop 1
	)
)

(instance atp4 of ForestView
	(properties
		x 109
		y 119
		view 414
		loop 1
		cel 6
	)
)

(instance atp5 of ForestView
	(properties
		x 292
		y 189
		view 417
		loop 1
	)
)

(instance atp6 of ForestView
	(properties
		x 302
		y 77
		view 417
		loop 1
		cel 1
	)
)
