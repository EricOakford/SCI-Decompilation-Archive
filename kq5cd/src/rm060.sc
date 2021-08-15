;;; Sierra Script 1.0 - (do not remove this comment)
(script# 60)
(include sci.sh)
(use Main)
(use Intrface)
(use castle)
(use KQ5Room)
(use Polygon)
(use RFeature)
(use Motion)
(use System)

(public
	rm060 0
)

(local
	local0
	[local1 26] = [0 160 16 160 8 187 259 187 307 170 297 167 285 159 295 153 279 134 277 0 319 0 319 189 0 189]
	[local27 18] = [0 0 81 0 81 137 70 141 79 143 74 150 57 150 37 158 0 155]
)
(instance rm060 of KQ5Room
	(properties
		picture 60
		horizon 140
		north 59
		west 61
	)
	
	(method (init)
		(super init:)
		(= global357 228)
		(= global358 152)
		(= global355 58)
		(= global356 167)
		(= global345 29)
		(= global346 160)
		(= magicDoorX 231)
		(= magicDoorY 145)
		(self
			setFeatures: firePlace doorWay1 doorWay2 birdMen room
			setRegions: 550
			addObstacle: poly1 poly2
		)
		(switch prevRoomNum
			(north
				(curRoom setScript: enterNorth)
			)
			(else 
				(curRoom setScript: enterLeft)
			)
		)
		(poly1 points: @local1 size: 13)
		(poly2 points: @local27 size: 9)
	)
	
	(method (doit &tmp temp0 egoY [temp2 2])
		(= egoY (ego y?))
		(cond 
			(script (script doit:))
			(
			(and (== wizardState 3) (ego inRect: 116 151 237 181))
				(if (Random 0 1)
					(= wizardX 99)
					(= wizardY 146)
					(= wizardAngle 135)
					(= global354 225)
				else
					(= wizardX 273)
					(= wizardY 151)
					(= wizardAngle 225)
					(= global354 135)
				)
				((ScriptID 550 7) init: setScript: (ScriptID 550 12))
			)
			((ego inRect: 0 153 13 165) (curRoom setScript: exitLeft))
			(
				(and
					(ego edgeHit?)
					(== 59 (self edgeToRoom: (ego edgeHit?)))
				)
				(if
					(and
						(> ((ScriptID 550 3) x?) 0)
						(< (ego distanceTo: (ScriptID 550 3)) 90)
						(== henchmanState 3)
					)
					(= henchmanState 5)
				)
				(if (== henchmanState 4) (= henchmanState 5))
				(curRoom newRoom: 59)
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

(instance firePlace of RFeature
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
					(SpeakAudio 631)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance doorWay1 of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0008))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 632)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance doorWay2 of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $4000))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 633)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance birdMen of RFeature
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
					(SpeakAudio 634)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 636)
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
				(JOY_UPRIGHT
					(SpeakAudio 635)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance exitLeft of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(CastleHandsOff)
				(ego
					ignoreActors: 1
					illegalBits: 0
					setMotion: MoveTo -19 159 self
				)
			)
			(1
				(if (!= henchmanState 4)
					(= henchmanState 0)
					(curRoom newRoom: 61)
				)
			)
		)
	)
)

(instance enterLeft of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(CastleHandsOff)
				(ego
					init:
					illegalBits: 0
					posn: -19 159
					setMotion: MoveTo 35 159 self
				)
				(if (and (not wizardTimer) (not henchmanTimer))
					(if (> (Random 0 100) 50)
						((ScriptID 550 3) init:)
					else
						(InitCat)
					)
				)
			)
			(1
				(CastleHandsOn)
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

(instance enterNorth of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(CastleHandsOff)
				(if (== henchmanState 6)
					(self changeState: 90)
				else
					(if (and (not wizardTimer) (not henchmanTimer))
						(if (> (Random 0 100) 25)
							((ScriptID 550 3) init:)
						else
							(InitCat)
						)
					)
					(if (== henchmanState 8)
						((ScriptID 550 3)
							init:
							setScript: 0
							view: 894
							setLoop: 4
							posn: 175 134
							show:
							stopUpd:
						)
					)
					(ego
						init:
						posn: (ego x?) 141
						setMotion: MoveTo (ego x?) 151 self
					)
				)
			)
			(1
				(CastleHandsOn)
				(client setScript: 0)
			)
			(90
				(ego init: hide: normal: 0)
				((ego head?) hide:)
				((ScriptID 550 3)
					init:
					show:
					setScript: 0
					view: 888
					setLoop: 2
					cel: 1
					posn: (ego x?) 160
					cycleSpeed: 4
					setCycle: End self
				)
			)
			(91
				((ScriptID 550 3)
					view: 896
					setLoop: 3
					cycleSpeed: 0
					setLoop: -1
					setCycle: Walk
					setMotion: MoveTo (ego x?) magicDoorY self
				)
			)
			(92
				(= henchmanState 5)
				(curRoom newRoom: 59)
			)
		)
	)
)
