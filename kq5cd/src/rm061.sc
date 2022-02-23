;;; Sierra Script 1.0 - (do not remove this comment)
(script# 61)
(include game.sh)
(use Main)
(use Intrface)
(use castle)
(use KQ5Room)
(use Polygon)
(use RFeature)
(use Motion)
(use System)

(public
	rm061 0
)

(local
	[local0 30] = [319 189 0 189 0 0 137 0 171 124 168 130 131 131 116 142 36 142 16 168 39 168 14 186 300 186 281 173 319 173]
	[local30 14] = [319 163 262 163 228 151 203 126 179 126 157 0 319]
)
(instance rm061 of KQ5Room
	(properties
		picture 61
		north 62
		east 60
	)
	
	(method (init)
		(super init:)
		(= global345 240)
		(= global346 173)
		(= global355 44)
		(= global356 164)
		(= global357 247)
		(= global358 176)
		(= magicDoorX 51)
		(= magicDoorY 159)
		(self
			setFeatures: statue doorWay1 doorWay2 room
			setRegions: 550
			addObstacle: poly1 poly2
		)
		(switch prevRoomNum
			(north
				(curRoom setScript: goDownstairs)
			)
			(else 
				(curRoom setScript: enterRight)
			)
		)
		(if
			(and
				(Btst 104)
				(not wizardTimer)
				(not henchmanTimer)
				(> (Random 0 100) 25)
			)
			(InitCat)
		)
		(Bset 104)
		(poly1 points: @local0 size: 15)
		(poly2 points: @local30 size: 7)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script (script doit:))
			(
			(and (== wizardState 3) (ego inRect: 29 133 174 184))
				(if (Random 0 1)
					(= wizardX 46)
					(= wizardY 153)
					(= wizardAngle 135)
					(= global354 225)
				else
					(= wizardX 243)
					(= wizardY 157)
					(= wizardAngle 225)
					(= global354 135)
				)
				((ScriptID 550 7) init: setScript: (ScriptID 550 12))
			)
			((ego inRect: 283 162 312 173) (curRoom setScript: exitRight))
			((& (ego onControl:) $4000) (curRoom setScript: goUpstairs))
		)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp [temp0 100])
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
)

(instance statue of RFeature
	(properties
		nsTop 4
		nsLeft 50
		nsBottom 136
		nsRight 121
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
					(SpeakAudio 637)
					(event claimed: 1)
				)
				(verbDo
					(SpeakAudio 641)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance doorWay1 of RFeature
	(properties
		nsTop 48
		nsLeft 266
		nsBottom 180
		nsRight 308
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
					(SpeakAudio 638)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance doorWay2 of RFeature
	(properties
		nsTop 35
		nsLeft 153
		nsBottom 124
		nsRight 206
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
					(SpeakAudio 639)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance room of RFeature
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
					(SpeakAudio 640)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance goDownstairs of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(CastleHandsOff)
				(ego
					init:
					illegalBits: 0
					ignoreActors: 1
					blocks: 0
					posn: 155 7
					setMotion: MoveTo 176 124 self
				)
			)
			(1
				(ego view: 0)
				(CastleHandsOn)
				(if
					(and
						(> (Random 0 100) 50)
						(== catState 0)
						(not wizardTimer)
						(not henchmanTimer)
					)
					((ScriptID 550 3) init:)
				)
				(client setScript: 0)
			)
		)
	)
)

(instance exitRight of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(CastleHandsOff)
				(ego
					ignoreActors: 1
					illegalBits: 0
					setMotion: MoveTo 337 169 self
				)
			)
			(1 (curRoom newRoom: 60))
		)
	)
)

(instance enterRight of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				(CastleHandsOff)
				(ego
					init:
					illegalBits: 0
					blocks: 0
					ignoreActors: 1
					posn: 337 169
					setMotion: MoveTo 274 169 self
				)
			)
			(2
				(CastleHandsOn)
				(if
					(and
						(> (Random 0 100) 50)
						(== catState 0)
						(not wizardTimer)
						(not henchmanTimer)
					)
					((ScriptID 550 3) init:)
				)
				(client setScript: 0)
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

(instance goUpstairs of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(CastleHandsOff)
				(ego
					illegalBits: 0
					ignoreActors: 1
					ignoreHorizon: 1
					setMotion: MoveTo 173 107 self
				)
			)
			(1
				(if (!= henchmanState 4)
					(if (< (ego distanceTo: (ScriptID 550 3)) 30)
						((ScriptID 550 3) setScript: 0 setMotion: 0)
					)
					(ego setMotion: MoveTo 155 7 self)
				else
					(client setScript: 0)
				)
			)
			(2 (curRoom newRoom: 62))
		)
	)
)
