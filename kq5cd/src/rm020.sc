;;; Sierra Script 1.0 - (do not remove this comment)
(script# 20)
(include game.sh)
(use Main)
(use Intrface)
(use KQ5Room)
(use Polygon)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	rm020 0
)

(local
	[local0 18] = [0 0 69 0 218 85 191 122 173 137 89 133 84 143 25 170 0 170]
	[local18 26] = [0 185 106 164 155 168 212 184 279 157 231 131 231 119 217 102 249 88 226 74 319 0 319 189 0 189]
)
(instance rm020 of KQ5Room
	(properties
		picture 20
		horizon 90
		north 21
		west 26
	)
	
	(method (init &tmp temp0)
		(super init:)
		(switch prevRoomNum
			(north (ego posn: 227 91))
			(208
				(ego posn: gGEgoX gGEgoY view: 0)
				(NormalEgo 0 0)
			)
			(else  (ego posn: 13 185))
		)
		(self
			setFeatures: path20 forest
			setRegions: 200 551 552
			addObstacle: poly1 poly2
		)
		(toad setScript: toadScript init:)
		(ego init:)
		(poly1 points: @local0 size: 9)
		(poly2 points: @local18 size: 13)
	)
	
	(method (doit &tmp temp0)
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
)

(instance toadScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 3 12)))
			(1
				(toad
					posn: 62 189
					ignoreActors: 1
					setCycle: Walk
					setMotion: MoveTo 271 152 self
				)
			)
			(2 (= seconds (Random 3 12)))
			(3
				(toad setMotion: MoveTo 127 134 self)
			)
			(4
				(client setScript: 0 dispose:)
			)
		)
	)
)

(instance path20 of RFeature
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
				(verbLook
					(SpeakAudio 335)
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
				(verbLook
					(SpeakAudio 336)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance toad of Actor
	(properties
		view 459
		illegalBits $0000
	)
	
	(method (doit)
		(super doit:)
		(if (== cel 1)
			(theMusic4
				number: (Random 99 100)
				loop: 1
				vol: 127
				play:
			)
		)
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
				(verbLook
					(SpeakAudio 337)
					(event claimed: 1)
				)
				(verbDo
					(SpeakAudio 338)
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
