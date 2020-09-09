;;; Sierra Script 1.0 - (do not remove this comment)
(script# ALLEY) ;4
(include game.sh)
(use Main)
(use Intrface)
(use StopWalk)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	demoAlley 0
)

(local
	[alleyPts 39] = [92 226 174 100 218 156 107 211 140 113 206 128 118 201 118 122 197 108 123 194 102 128 191 98 130 188 90 132 185 85 136 182 80 139 179 73 142 176 69]
	[local39 10] = [701 0 700 4 700 2 700 1 700]
	[local49 10] = [1 2 2 1 1 1 2 2 3 4]
)
(procedure (localproc_000c param1 param2)
	(return [local49 (+ (* param1 2) param2)])
)

(procedure (localproc_0018 param1 param2)
	(return [local39 (+ (* param1 2) param2)])
)

(class DemoAFeat of View
	(properties
		signal (| ignrAct ignrHrz notUpd) ;$6004
		owner 0
		coord 0
		type 0
		side 0
		illegalBits 0
	)
	
	(method (init theOwner)
		(= owner theOwner)
		(= view (localproc_0018 type 0))
		(= loop (+ (localproc_0018 type 1) side))
		(if (OneOf type 1 2) (self posn: 160 76 setPri: 0))
		(super init:)
	)
	
	(method (redraw scrBotPos &tmp distance [str 100])
		(= distance (- coord scrBotPos))
		(cond 
			((== type 0)
				(if (not (if (< -1 distance) (< distance 16)))
					(self hide:)
					(return)
				)
				(self
					posn: [alleyPts (+ (* distance 3) side)] [alleyPts (+ (* distance 3) 2)]
					cel: distance
					setPri: -1
					forceUpd:
					show:
				)
			)
			((OneOf type 1 2)
				(cond 
					((>= distance 16) (self hide:))
					((> distance 12)
						(= cel (- (self lastCel:) (- distance 12)))
						(self forceUpd: show:)
					)
					(else (= cel (self lastCel:)) (self forceUpd: show:))
				)
			)
		)
	)
)

(class DemoAlley of Room
	(properties
		length 0
		endType 0
		egoCoord 0
		isScrolling 0
		isMoving 0
		alleyFeatures 0
	)
	
	(method (init)
		(Load VIEW vAlley)
		(Load VIEW vAlleyDoor)
		(= isScrolling TRUE)
		(= alleyFeatures (List new:))
		(self add: length (localproc_000c endType 0) 0)
		(self add: length (localproc_000c endType 1) 1)
		(super init: &rest)
	)
	
	(method (doit)
		(super doit:)
		(if (not (if isMoving isScrolling)) (return))
		(if (and isScrolling (> egoCoord (- length 12)))
			(= isScrolling FALSE)
			(ego setDirection: dirN setCycle: StopWalk 4)
			(return)
		)
		(ego setCycle: Forward doit: setCycle: 0)
		(if
		(and (== (ego loop?) 3) (not (& (ego cel?) 1)))
			(alleyFeatures eachElementDo: #redraw egoCoord)
			(++ egoCoord)
		)
	)
	
	(method (dispose)
		(alleyFeatures dispose:)
		(super dispose:)
	)
	
	(method (add theCoord aType aSide &tmp newFeature)
		(alleyFeatures
			add:
				((= newFeature (DemoAFeat new:))
					type: aType
					coord: theCoord
					side: aSide
					init: self
					redraw: egoCoord
					yourself:
				)
		)
		(return newFeature)
	)
)

(instance demoAlley of DemoAlley
	(properties
		picture rAlley
		style IRISIN
		length 50
	)
	
	(method (init)
		(super init:)
		(ego
			loop: loopN
			init:
			view: vEgo
			posn: 160 175
			ignoreActors:
			illegalBits: 0
		)
		(self
			add: 5 0 1
			add: 10 0 0
			add: 15 0 1
			add: 10 0 1
			add: 15 0 0
			add: 15 0 1
			add: 20 0 0
			add: 22 0 1
			add: 30 0 1
			add: 35 0 0
			add: 35 0 1
		)
		(globalSound number: rKhaveenHouse loop: -1 playBed:)
		(= isMoving TRUE)
		(Print ALLEY 0 #at -1 180 #dispose)
	)
	
	(method (doit)
		(super doit:)
		(if (and (not script) (<= (ego y?) 79))
			(self setScript: exitScript)
		)
	)
)

(instance exitScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setPri: 4 setMotion: MoveTo 120 79 self)
			)
			(1
				(cls)
				(client newRoom: 5)
				(self dispose:)
			)
		)
	)
)
