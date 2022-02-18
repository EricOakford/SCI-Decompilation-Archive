;;; Sierra Script 1.0 - (do not remove this comment)
(script# 50)
(include sci.sh)
(use Main)
(use Intrface)
(use CodeCue)
(use KQ5Room)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	rm050 0
)

(local
	[local0 12] = [0 173 94 166 159 159 222 159 272 189 0 189]
	[local12 16] = [307 62 260 65 209 90 199 104 167 118 0 119 0 0 299]
	[local28 12] = [319 148 269 142 212 141 279 114 283 86 319 80]
	[local40 9] = [1003 90 75 4 11 25 23 31 31]
	[local49 18] = [1000 80 90 4 11 24 19 23 30 1000 116 62 4 11 24 19 23 30]
	[local67 12] = [0 7029 1 3077 0 0 0 3030 1 7771]
)
(instance rm050 of KQ5Room
	(properties
		picture 50
		horizon 70
		north 90
		east 90
		west 49
	)
	
	(method (init)
		(super init:)
		(LoadMany 128 68 656 655)
		(self
			setFeatures: arch path50
			addObstacle: poly1 poly2 poly3
		)
		(switch prevRoomNum
			(west (ego posn: 27 138))
			(else  (ego posn: 297 75))
		)
		(ego
			view: (if (Btst 74) 656 else 0)
			init:
			ignoreActors: 0
			setStep: 3 2
		)
		(if (Btst 55)
			(cedric init: stopUpd:)
			(theMusic number: 818 loop: -1 vol: 127 playBed:)
		)
		(poly1 points: @local0 size: 6)
		(poly2 points: @local12 size: 8)
		(poly3 points: @local28 size: 6)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script (script doit:))
			(
				(and
					(ego edgeHit?)
					(= temp0 (self edgeToRoom: (ego edgeHit?)))
				)
				(curRoom newRoom: temp0)
			)
			((& (ego onControl: 0) $0020) (HandsOff) (self setScript: fallScript))
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
	
	(method (newRoom n)
		(super newRoom: n)
	)
)

(instance fallScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ego head?) hide:)
				(ego
					normal: 0
					view: (if (Btst 74) 655 else 68)
					setLoop: 0
					signal: (| (ego signal?) $4000)
					cel: 0
					cycleSpeed: 3
					setCycle: EndLoop self
					illegalBits: 0
				)
			)
			(1
				(ego yStep: 8 setMotion: MoveTo (ego x?) 230)
				(theAudio number: 7053 loop: 1 play: self)
			)
			(2
				(= seconds 2)
				(= inCartoon 0)
			)
			(3
				(theAudio number: 8068 play: self)
			)
			(4
				(= deathMessage 412)
				(EgoDead)
			)
		)
	)
)

(instance getCedScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cedric startUpd:)
				(proc762_1 @local49 3076 self)
			)
			(1
				(ego
					ignoreActors:
					setMotion: PolyPath (+ (cedric x?) 11) (- (cedric y?) 4) self
				)
			)
			(2
				((ego head?) hide:)
				(ego
					normal: 0
					view: 658
					loop: 1
					cel: 0
					cycleSpeed: 2
					setCycle: EndLoop self
				)
				(cedric dispose:)
			)
			(3
				(SolvePuzzle 3)
				((ego head?) show:)
				(ego
					normal: 1
					view: 656
					setLoop: -1
					cycleSpeed: 0
					setCycle: KQ5SyncWalk
				)
				(= cycles 1)
			)
			(4
				(proc762_0 @local40 @local49 @local67 self)
			)
			(5
				(= cycles 1)
				(theMusic fade:)
			)
			(6
				(Bclr 55)
				(Bset 74)
				(= inCartoon 0)
				(HandsOn)
				(theMusic number: 814 loop: -1 vol: 127 playBed:)
				(self dispose:)
			)
		)
	)
)

(instance arch of RFeature
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
					(SpeakAudio 542)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance path50 of RFeature
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
					(SpeakAudio 543)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance cedric of Actor
	(properties
		x 112
		y 154
		view 658
		loop 3
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
					(SpeakAudio 544)
					(event claimed: 1)
				)
				(JOY_DOWN
					(SpeakAudio 3003)
					(event claimed: 1)
				)
				(JOY_DOWNRIGHT
					(if
					(!= (inventory indexOf: (theIconBar curInvIcon?)) 28)
						(SpeakAudio 545)
						(event claimed: 1)
					else
						(event claimed: 0)
					)
				)
				(JOY_RIGHT
					(HandsOff)
					(curRoom setScript: getCedScript)
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
