;;; Sierra Script 1.0 - (do not remove this comment)
(script# 590)
(include sci.sh)
(use Main)
(use GloryRm)
(use ForestView)
(use Polygon)

(public
	rm590 0
)

(instance rm590 of GloryRm
	(properties
		picture 410
		horizon 70
		north 589
		west 585
		topX 180
	)
	
	(method (init)
		(if debugging (= Night 1))
		(if
			(or
				(and Night (not (Btst 64)))
				(and (Btst 64) (not (Btst 59)))
				(and (== global365 850) (== prevRoomNum 810))
			)
			(if (Btst 64) (Bset 66))
			(if (Btst 59) (Bset 65))
			(self setRegions: 53)
		)
		(self setRegions: 50)
		(super init: &rest)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						-300
						-300
						149
						-300
						149
						96
						142
						101
						142
						121
						161
						121
						161
						135
						98
						135
						61
						129
						-300
						129
					yourself:
				)
				((Polygon new:)
					type: 2
					init:
						619
						-300
						619
						489
						-300
						489
						-300
						175
						200
						175
						200
						147
						261
						147
						261
						126
						207
						126
						207
						117
						149
						117
						149
						105
						247
						79
						247
						-300
					yourself:
				)
		)
		(curRoom
			addPoly:
				((Polygon new:)
					init: -300 123 34 132 20 141 29 148 50 139 -300 489
					yourself:
				)
				130
				((Polygon new:)
					init: 69 489 110 143 160 142 150 107 237 489
					yourself:
				)
				130
				((Polygon new:)
					init: 202 50 184 74 158 66 171 50
					yourself:
				)
				130
		)
		(if Night
			(atp5 view: 418 loop: 1 cel: 2 x: 87 y: 189)
			(atp1 view: 418 loop: 1 cel: 1 x: 258 y: 189)
		)
		(atp1 init: setPri: 180)
		(atp2 init: setPri: 105)
		(atp3 init:)
		(atp4 init: setPri: 127)
		(atp5 init: setPri: 180)
		(atp6 init: setPri: 169)
		(if (Btst 380) (theGame save: 1))
	)
)

(instance atp1 of ForestView
	(properties
		x 144
		y 146
		view 412
		cel 1
	)
)

(instance atp2 of ForestView
	(properties
		x 154
		y 56
		view 411
		cel 1
	)
)

(instance atp3 of ForestView
	(properties
		x 292
		y 110
		view 411
		cel 2
	)
)

(instance atp4 of ForestView
	(properties
		x 64
		y 4
		view 415
	)
)

(instance atp5 of ForestView
	(properties
		x 3
		y 170
		view 412
	)
)

(instance atp6 of ForestView
	(properties
		x 210
		y 119
		view 414
		cel 6
	)
)
