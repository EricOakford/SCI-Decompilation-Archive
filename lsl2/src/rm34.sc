;;; Sierra Script 1.0 - (do not remove this comment)
(script# 34)
(include game.sh)
(use Main)
(use Intrface)
(use Extra)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm34 0
)

(local
	drowningView
	henchwomanAppeared
	innerTubeGuyHead
	guyWithCap
	guyWithMirror
	ripple1
	ripple2
	innerTubeGuy
	local8
	theBSetter
	drowningInLeisureSuit
	henchwoman
	canFollowHenchwoman
)
(instance rm34 of Room
	(properties
		picture 34
		horizon 5
	)
	
	(method (init)
		(Load VIEW 310)
		(Load VIEW 134)
		(Load VIEW 135)
		(Load VIEW 137)
		(Load VIEW 138)
		(Load VIEW 139)
		(super init:)
		((View new:)
			view: 310
			loop: 2
			cel: 6
			posn: 11 136
			setPri: 10
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 310
			loop: 2
			cel: 0
			posn: 45 136
			setPri: 10
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 310
			loop: 2
			cel: 1
			posn: 80 136
			setPri: 10
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 310
			loop: 2
			cel: 1
			posn: 115 136
			setPri: 10
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 310
			loop: 2
			cel: 2
			posn: 177 136
			setPri: 10
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 310
			loop: 2
			cel: 3
			posn: 224 136
			setPri: 10
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 310
			loop: 2
			cel: 4
			posn: 263 136
			setPri: 10
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 310
			loop: 2
			cel: 5
			posn: 303 136
			setPri: 10
			ignoreActors:
			addToPic:
		)
		((= ripple1 (Prop new:))
			view: 310
			setLoop: 0
			posn: 128 53
			setPri: 2
			setCycle: Forward
			cycleSpeed: 4
			ignoreActors:
			isExtra: TRUE
			init:
		)
		((= ripple2 (Prop new:))
			view: 310
			setLoop: 1
			posn: 119 68
			setPri: 3
			setCycle: Forward
			cycleSpeed: 7
			ignoreActors:
			init:
		)
		((= innerTubeGuyHead (Extra new:))
			view: 310
			setLoop: 3
			posn: 49 111
			setPri: 10
			cycleSpeed: 2
			minPause: 20
			maxPause: 30
			ignoreActors:
			isExtra: TRUE
			init:
		)
		((= guyWithCap (Extra new:))
			view: 310
			setLoop: 5
			posn: 228 106
			setPri: 10
			cycleSpeed: 1
			minPause: 16
			maxPause: 40
			ignoreActors:
			init:
		)
		((= guyWithMirror (Extra new:))
			view: 310
			setLoop: 6
			posn: 259 117
			setPri: 10
			cycleSpeed: 3
			minPause: 12
			maxPause: 31
			ignoreActors:
			init:
		)
		(self setRegions: SHIP setScript: rm34Script)
		(if (== currentEgoView 132)
			(self setRegions: 8)
			(= henchView 311)
			(Load VIEW henchView)
			((= henchwoman (Actor new:))
				view: henchView
				posn: 155 233
				illegalBits: -32768
				observeControl: 256
				setCycle: Walk
				init:
				setScript: henchScript
			)
		)
		((= innerTubeGuy (Actor new:))
			view: 310
			setLoop: 4
			illegalBits: -513
			posn: 194 85
			init:
			setMotion: Wander
			moveSpeed: 5
			cycleSpeed: 5
			setStep: 1 1
		)
		(if (== prevRoomNum 134)
			(= currentStatus egoSwimming)
			(ego
				view: 134
				setLoop: -1
				setCycle: Forward
				setPri: -1
				setMotion: 0
				setStep: 3 2
				illegalBits: -1
				ignoreControl: 512 256
				posn: 157 83
				cycleSpeed: 1
				moveSpeed: 1
				baseSetter: (= theBSetter BSetter)
				init:
			)
		else
			(NormalEgo 3)
			(ego posn: 157 183 init:)
		)
		(User canControl: TRUE canInput: TRUE)
	)
)

(instance rm34Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and (& (ego onControl:) $0400) (== currentStatus egoNormal))
			(ego posn: 55 88)
			(self changeState: 1)
		)
		(if
		(and (& (ego onControl:) $1000) (== currentStatus egoNormal))
			(ego posn: 255 88)
			(self changeState: 1)
		)
		(if
		(and (& (ego onControl:) $0100) (== currentStatus egoNormal))
			(self changeState: 1)
		)
		(if (== 3 (ego edgeHit?))
			(if (== local8 0)
				(curRoom newRoom: 31)
			else
				(Print 34 0)
				(Print 34 1 #draw)
				(= currentStatus egoCaptured)
				(curRoom newRoom: 95)
			)
		)
		(if
		(and henchwomanIsHere canFollowHenchwoman (> (ego y?) 181))
			(= canFollowHenchwoman FALSE)
			(= local8 1)
			(curRoom south: 95)
			(Print 34 2)
			(HandsOff)
			(ego setMotion: MoveTo (ego x?) 234)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(User canControl: FALSE)
				(= currentEgoView (ego view?))
				(= currentStatus egoDrowning)
				(if (== currentEgoView 100)
					(= drowningInLeisureSuit TRUE)
					(= drowningView 137)
					(User canInput: FALSE)
				else
					(= drowningView 138)
				)
				(ego
					view: drowningView
					illegalBits: -1
					ignoreControl: 256 512
					cycleSpeed: 1
					moveSpeed: 1
					setStep: 2 2
					setCycle: Forward
					setMotion: Wander
				)
				(= seconds 5)
			)
			(2
				(= currentStatus egoStopped)
				(User canInput: FALSE)
				(ego cel: 0 setCycle: EndLoop self setMotion: 0)
			)
			(3
				(if (== drowningInLeisureSuit TRUE)
					(Print 34 27)
				else
					(Print 34 28)
					(Print 34 29)
				)
				(= currentStatus egoDead)
				(ego hide:)
			)
			(4
				(= seconds (= cycles 0))
				(= currentStatus egoSwimming)
				(User canControl: TRUE)
				(ego
					view: 134
					setStep: 3 2
					setLoop: -1
					setMotion: 0
					baseSetter: (= theBSetter BSetter)
				)
			)
			(5
				(= seconds (= cycles 0))
				(HandsOff)
				(= currentStatus egoDiving)
				(ego
					view: 135
					loop: (if (or (== (ego loop?) 3) (== (ego loop?) 1))
						1
					else
						0
					)
					cel: 0
					setCycle: EndLoop self
				)
			)
			(6
				(ego hide:)
				(curRoom newRoom: 134)
			)
			(7
				(= seconds (= cycles 0))
				(if (== sunscreenState sunscreenAPPLIED) (= sunscreenState sunscreenAFTERSWIM))
				(ego
					view: currentEgoView
					setLoop: -1
					setPri: -1
					setMotion: 0
					setCycle: Walk
					cycleSpeed: 0
					moveSpeed: 0
					ignoreActors: 0
					illegalBits: 256
					observeControl: 512
					baseSetter: 0
				)
				(= cycles 12)
			)
			(8 (Print 34 30) (NormalEgo))
			(9
				(HandsOff)
				(if (not tookSwimInShipPool)
					(= tookSwimInShipPool TRUE)
					(theGame changeScore: 3)
				)
				(PrintOk)
				(if (< (ego y?) 113)
					(ego setMotion: MoveTo 160 112 self)
				else
					(self changeState: 10)
				)
			)
			(10
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 163 134 self
				)
			)
			(11
				(ego
					view: 139
					cycleSpeed: 1
					setMotion: 0
					setPri: 10
					setLoop: 0
					posn: 171 133
					cel: 0
					setCycle: EndLoop self
				)
			)
			(12
				(= currentStatus egoSitting)
				(Print 34 31)
				(= seconds 5)
			)
			(13
				(if (== sunscreenState sunscreenAPPLIED)
					(User canInput: TRUE)
					(Print 34 32)
					(if (not henchwomanAppeared) (henchScript changeState: 1))
				else
					(= currentStatus egoStopped)
					(ego
						setLoop: 1
						setCel: 0
						cycleSpeed: 5
						setCycle: EndLoop self
					)
				)
			)
			(14
				(Print 34 33 #at -1 20 #draw)
				(= currentStatus egoDead)
			)
			(15
				(= seconds (= cycles 0))
				(PrintOk)
				(ego cycleSpeed: 1 setCycle: BegLoop self)
				(if (== 1 (henchScript state?))
					(henchScript changeState: 255)
				)
			)
			(16
				(ego posn: 163 123)
				(NormalEgo)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/man,children') (Print 34 3))
			(if (Said '/airport,barstool')
				(if (!= currentStatus egoSitting)
					(Print 34 4)
				else
					(Print 34 5)
				)
			)
			(if (Said '<below/airport,barstool')
				(PrintNothingSpecial)
			)
			(if (Said '/inner') (Print 34 6))
			(if (Said '/flower,palm') (Print 34 7))
			(if (Said '[/fluid,craft,airport]')
				(cond 
					((== currentStatus egoSwimming) (Print 34 8))
					((== currentStatus egoDrowning) (Print 34 9))
					((== currentStatus egoSitting) (Print 34 10))
					(else (Print 34 11) (Print 34 12))
				)
			)
		)
		(if
			(or
				(Said '(get<in),board/fluid,fluid')
				(Said 'bathing')
			)
			(cond 
				((== currentStatus egoSwimming) (Print 34 13))
				((!= currentStatus egoDrowning) (Print 34 14) (Print 34 15))
				(else (PrintOk) (self changeState: 4))
			)
		)
		(if
			(or
				(Said 'disembark/fluid,fluid')
				(Said 'get,climb<off')
				(Said 'apply,climb/ladder')
			)
			(cond 
				((!= currentStatus egoSwimming) (PrintNotNow))
				((not (& (ego onControl:) $0100)) (PrintNotCloseEnough))
				(else (PrintOk) (self changeState: 7))
			)
		)
		(if
		(or (Said 'board,bathing/underwater') (Said 'dive'))
			(cond 
				((== currentStatus egoDrowning) (Print 34 16))
				((!= currentStatus egoSwimming) (Print 34 17))
				((& (ego onControl:) $0100) (Print 34 18))
				(else (PrintOk) (self changeState: 5))
			)
		)
		(if
			(or
				(Said 'lie,board,bath[/bed,airport,barstool]')
				(Said 'bath[/down,airport,barstool]')
			)
			(cond 
				((== currentStatus egoSitting) (Print 34 19))
				((not (ego inRect: 139 102 199 134)) (PrintNotCloseEnough))
				((!= currentEgoView 132) (Print 34 20))
				((!= currentStatus egoNormal) (PrintNotNow))
				(else (self changeState: 9))
			)
		)
		(if
			(or
				(Said 'new,(new[<up]),(get<up)')
				(Said 'disembark[/barstool]')
			)
			(cond 
				((== currentStatus egoNormal) (Print 34 13))
				(
					(and
						(<= (henchScript state?) 4)
						(== henchwomanIsHere TRUE)
					)
					(Print 34 21)
				)
				((!= currentStatus egoSitting) (PrintNotNow))
				(else (self changeState: 15))
			)
		)
		(if (or (Said 'breathe') (Said 'get/breath'))
			(PrintOk)
		)
		(if
			(or
				(Said 'jerk,jerk<over')
				(Said 'lie<on/front,stomach,ear')
			)
			(Print 34 22)
		)
		(if (Said '/bimbo>')
			(cond 
				((Said 'call/')
					(if henchwomanIsHere
						(Print (Format @str 34 23 introductoryPhrase))
					else
						(Print 34 24)
					)
				)
				((Said 'look/') (if henchwomanIsHere (Print 34 25) else (Print 34 26)))
			)
		)
	)
)

(instance henchScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(= henchwomanAppeared TRUE)
				(= seconds (Random 5 10))
			)
			(2
				(if (!= currentStatus egoSitting)
					(self changeState: 1)
				else
					(henchwoman setMotion: MoveTo 155 129 self)
					(= henchwomanIsHere TRUE)
					(NotifyScript 8 1)
				)
			)
			(3
				(henchwoman setMotion: MoveTo 160 129 self)
			)
			(4
				(Print 34 34)
				(Print 34 35 #at -1 152)
				(= seconds 5)
			)
			(5
				(Print 34 36)
				(henchwoman setMotion: MoveTo 155 129 self)
				(= canFollowHenchwoman 1)
			)
			(6
				(henchwoman setMotion: MoveTo 155 234 self)
			)
			(7 (= seconds 10))
			(8
				(henchwoman dispose:)
				(= henchView 0)
				(= henchwomanIsHere FALSE)
				(= canFollowHenchwoman FALSE)
			)
		)
	)
)

(instance BSetter of Code
	(properties)
	
	(method (doit param1)
		(param1 brBottom: (+ (param1 y?) 5))
		(param1 brTop: (- (param1 y?) 3))
		(param1 brLeft: (- (param1 x?) 10))
		(param1 brRight: (+ (param1 x?) 10))
	)
)
