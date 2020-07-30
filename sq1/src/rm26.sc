;;; Sierra Script 1.0 - (do not remove this comment)
(script# 26)
(include game.sh)
(use Main)
(use SQRoom)
(use Polygon)
(use Feature)

(public
	rm26 0
)

(instance rm26 of SQRoom
	(properties
		lookStr {This is just south of the gigundasaurus remains. Ligament jerky-deep in the joints is all that holds some of these incredibly huge skeletal components together.}
		picture 26
		horizon 20
		north 23
		east 27
		south 18
		west 25
		walkOffTop 1
	)
	
	(method (init)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						319 -10 319 52 269 59 246 86 286 114 314 132 309 154
						291 163 267 163 230 140 253 132 190 102 177 86 223 22
						113 22 83 -10
					yourself:
				)
		)
		(switch prevRoomNum
			(east (= style SCROLLRIGHT) (HandsOn))
			(west (= style SCROLLLEFT) (HandsOn))
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
		(sand init:)
		(self setRegions: KERONA)
		((ScriptID KERONA 1)
			x: 171
			y: 89
			approachX: 166
			approachY: 89
		)
		(super init:)
	)
	
	(method (doit)
		(if (& (ego onControl: origin) cYELLOW)
			(ego setPri: 10)
		else
			(ego setPri: -1)
		)
		(super doit: &rest)
	)
)

(instance sand of Feature
	(properties
		description {sand}
		onMeCheck $4000
		lookStr {The sand of Kerona has a reddish-orange color to it. Were you a geologist instead of a janitor, you might find this fact interesting.}
	)
)
