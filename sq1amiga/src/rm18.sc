;;; Sierra Script 1.0 - (do not remove this comment)
(script# 18)
(include sci.sh)
(use Main)
(use SQRoom)

(public
	rm18 0
)

(instance rm18 of SQRoom
	(properties
		lookStr {In the distance stand the skeletal remains of of a massive beast of some type, the likes of which you never saw on Xenon. You hope it's a one of a kind relic.}
		picture 18
		style $0008
		horizon 161
		east 138
		south 38
		west 338
	)
	
	(method (init)
		(switch prevRoomNum
			(west (ego y: 166 init:))
			(east (ego y: 166 init:))
			(south (ego x: 160 init:))
			(else 
				(= north prevRoomNum)
				(ego
					x:
					(switch (= north prevRoomNum)
						(25 54)
						(26 160)
						(27 275)
					)
					init:
				)
			)
		)
		(self setRegions: 704)
		(super init:)
	)
	
	(method (doit)
		(= north
			(cond 
				((<= (ego x?) horizon) 25)
				((>= (ego x?) 212) 27)
				(else 26)
			)
		)
		(super doit:)
	)
)
