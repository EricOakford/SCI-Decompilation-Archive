;;; Sierra Script 1.0 - (do not remove this comment)
(script# 520)
(include game.sh)
(use Main)
(use brain)
(use SQRoom)
(use Motion)
(use System)

(public
	rm520 0
)

(instance rm520 of SQRoom
	(properties
		picture 520
		style FADEOUT
		north 505
		east 525
		west 515
	)
	
	(method (init)
		(if (== prevRoomNum 505)
			(self
				setScript: fromNorthScript 0 (if (< (ego x?) 90) 0 else 1)
			)
		else
			(HandsOn)
		)
		(ego init:)
		(self setRegions: BRAIN)
		(super init:)
		(switch (brain level?)
			(1
				(brain
					makePolygon:
						0 159 60 159 95 180 208 180 246 162 246 105 210 121
						210 156 96 156 96 118 63 106 63 134 0 134 0 0 319 0
						319 189 0 189
				)
			)
			(2
				(brain
					makePolygon: 0 130 59 130 96 149 207 149 244 132 319 132 319 189 0 189
				)
				(brain
					makePolygon: 0 0 319 0 319 112 250 112 207 128 104 128 58 110 0 110
				)
			)
			(3
				(brain
					makePolygon: 0 97 61 97 93 109 206 109 246 97 319 97 319 189 0 189
				)
				(brain makePolygon: 0 37 78 37 68 75 0 75)
				(brain
					makePolygon: 0 0 319 0 319 24 241 24 206 7 108 7 81 24 0 24
				)
				(brain makePolygon: 319 76 253 76 244 35 319 35)
				(brain
					makePolygon:
						94 81 94 61 90 52 119 40 204 40 226 51
						226 65 230 81 214 87 108 87
				)
			)
		)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			(
			(and (== (brain level?) 1) (IsObjectOnControl ego cMAGENTA)) (brain level: 2) (self newRoom: north))
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(narrator modNum: 500 say: 2)
			)
			(V_TALK
				(narrator modNum: 510 say: 3)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance fromNorthScript of Script
	(properties)
	
	(method (changeState newState &tmp egoX egoY newX newY)
		(switch (= state newState)
			(0
				(HandsOff)
				(if register
					(= egoX 235)
					(= egoY 141)
					(= newX 235)
					(= newY 167)
				else
					(= egoX 91)
					(= egoY 144)
					(= newX 91)
					(= newY 172)
				)
				(ego posn: egoX egoY setMotion: MoveTo newX newY self)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)
