;;; Sierra Script 1.0 - (do not remove this comment)
(script# 26)
(include sci.sh)
(use Main)
(use Intrface)
(use KQ5Room)
(use PolyPath)
(use Polygon)
(use RFeature)
(use Actor)

(public
	rm026 0
)

(local
	local0
	[local1 34] = [226 148 226 151 184 151 184 155 170 155 170 162 105 162 105 166 55 166 55 170 32 170 32 154 42 154 42 142 66 142 66 137 226 137]
	[local35 8] = [0 187 319 187 319 189 0 189]
	[local43 18] = [0 0 66 0 186 57 180 65 172 101 190 111 174 125 19 138 0 144]
	[local61 18] = [319 127 220 127 200 108 216 96 204 93 187 76 199 62 186 44 319]
)
(instance rm026 of KQ5Room
	(properties
		picture 26
		horizon 90
		north 22
		east 20
		west 25
	)
	
	(method (init)
		(super init:)
		(switch prevRoomNum
			(east (ego posn: 312 141))
			(north (ego posn: 185 92))
			(208
				(ego posn: gGEgoX gGEgoY view: 0)
				(NormalEgo 0 0)
			)
			(else  (ego posn: 8 163))
		)
		(self
			setFeatures: path26 house forest
			setRegions: 200 551 552
			addObstacle: poly1 poly2 poly3 poly4
		)
		(ego view: 0 ignoreHorizon: 1 init:)
		(poly1 points: @local1 size: 17)
		(poly2 points: @local35 size: 4)
		(poly3 points: @local43 size: 9)
		(poly4 points: @local61 size: 9)
	)
	
	(method (doit &tmp temp0)
		(cond 
			((< (ego y?) 110) (if (not (& (ego signal?) $0010)) (ego setPri: 6)))
			((& (ego signal?) $0010) (ego setPri: -1))
		)
		(cond 
			(script (script doit:))
			(
				(and
					(ego edgeHit?)
					(= temp0 (self edgeToRoom: (ego edgeHit?)))
				)
				((ScriptID 200 1) register: temp0)
				(self setScript: (ScriptID 200 1) 0 (ego edgeHit?))
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
	
	(method (newRoom n)
		(ego setPri: -1)
		(super newRoom: n)
	)
)

(instance path26 of RFeature
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
					(SpeakAudio 388)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance house of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0004))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 389)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(ego setMotion: PolyPath 186 49)
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
					(SpeakAudio 390)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance toads of Actor
	(properties)
	
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
					(SpeakAudio 337)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(if (not local0)
						(++ local0)
						(SpeakAudio 338)
						(event claimed: 1)
					)
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

(instance poly4 of Polygon
	(properties
		type $0002
	)
)
