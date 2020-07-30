;;; Sierra Script 1.0 - (do not remove this comment)
(script# 25)
(include game.sh)
(use Main)
(use Intrface)
(use SQRoom)
(use Polygon)
(use Feature)

(public
	rm25 0
)

(instance rm25 of SQRoom
	(properties
		lookStr {This looks like it might have been a minimally developed, manipulative appendage for the fallen and fossilized beast.}
		picture 25
		horizon 20
		north 22
		east 26
		south 18
		west 38
		walkOffTop 1
	)
	
	(method (init)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init: 276 0 269 17 226 29 170 29 146 14 127 0
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						232 137 182 134 125 118 38 76 51 65 30 63 51 52
						275 52 309 94 277 108 233 93 231 87 205 84 179 96
						222 9 247 103 249 127
					yourself:
				)
		)
		(switch prevRoomNum
			(east (= style SCROLLRIGHT) (HandsOn))
			(north (= style SCROLLUP) (HandsOn))
			(south
				(ego x: 160)
				(= style FADEOUT)
			)
			(else
				(= style FADEOUT)
			)
		)
		(ego init:)
		(self setRegions: KERONA)
		((ScriptID KERONA 1)
			x: 47
			y: 56
			approachX: 42
			approachY: 56
		)
		(super init:)
		(if (and (> 72 (ego y?)) (> (ego y?) 49))
			(ego y: 51)
		)
		(claws init:)
	)
)

(instance claws of Feature
	(properties
		description {claws}
		onMeCheck $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 25 0)
				(Print 25 1)
			)
			(verbDo
				(Print 25 2)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)
