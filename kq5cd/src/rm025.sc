;;; Sierra Script 1.0 - (do not remove this comment)
(script# 25)
(include sci.sh)
(use Main)
(use Intrface)
(use KQ5Room)
(use Polygon)
(use RFeature)

(public
	rm025 0
)

(local
	local0
	[local1 20] = [0 56 36 56 38 66 70 83 77 106 70 131 156 172 319 173 319 189 0 189]
	[local21 20] = [76 62 76 0 319 0 319 136 184 133 131 124 125 114 192 104 197 86 106 86]
	[local41 10] = [309 133 187 131 133 122 129 115 281 93]
)
(instance rm025 of KQ5Room
	(properties
		picture 25
		north 24
		east 26
	)
	
	(method (init)
		(super init:)
		(switch prevRoomNum
			(north (ego posn: 55 69))
			(208
				(ego posn: gGEgoX gGEgoY view: 0)
				(NormalEgo 0 0)
			)
			(else  (ego posn: 311 153))
		)
		(self setFeatures: path25 forest setRegions: 200 551 552)
		(ego init:)
		(poly1 points: @local1 size: 10)
		(poly2 points: @local21 size: 10)
		(poly3 points: @local41 size: 5)
		(self addObstacle: poly1 poly2 poly3)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script (script doit:))
			(
				(and
					(ego edgeHit?)
					(= temp0 (self edgeToRoom: (ego edgeHit?)))
				)
				(self setScript: (ScriptID 200 1) 0 (ego edgeHit?))
			)
			((& (ego onControl: 0) $4000)
				((ScriptID 200 1) register: temp0)
				(self setScript: (ScriptID 200 1) 0 1)
			)
		)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
)

(instance path25 of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0002))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 386)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance forest of RFeature
	(properties
		nsBottom 200
		nsRight 320
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 387)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance poly1 of Polygon
	(properties
		type $0002
	)
)

(instance poly2 of Polygon
	(properties
		type $0002
	)
)

(instance poly3 of Polygon
	(properties
		type $0002
	)
)
