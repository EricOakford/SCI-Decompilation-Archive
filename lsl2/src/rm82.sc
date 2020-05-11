;;; Sierra Script 1.0 - (do not remove this comment)
(script# 82)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm82 0
)

(local
	holdingBomb
	bombHasWick
	bombLit
	bombDropped
	oldEgoX
	oldEgoY
	elevatorDoor
	steam3
	steam2
	steam1
	bottle
)
(instance theSound of Sound
	(properties
		number 6
	)
)

(instance rm82 of Room
	(properties
		picture 82
	)
	
	(method (init)
		(Load VIEW 715)
		(Load VIEW 184)
		(Load VIEW 103)
		(Load SOUND 1)
		(Load SOUND 6)
		(super init:)
		(theSound init:)
		((= steam1 (Prop new:))
			view: 715
			loop: 3
			posn: 142 124
			setCycle: Forward
			isExtra: TRUE
			init:
		)
		((= steam2 (Prop new:))
			view: 715
			loop: 2
			posn: 102 113
			setCycle: Forward
			isExtra: TRUE
			init:
		)
		((= steam3 (Prop new:))
			view: 715
			loop: 1
			posn: 24 110
			setCycle: Forward
			isExtra: TRUE
			init:
		)
		((= elevatorDoor (Prop new:))
			view: 715
			setLoop: 0
			posn: 75 146
			setPri: 11
			stopUpd:
			init:
		)
		(if debugging (ego get: iAirsickBag get: iHairRejuvenator get: iMatches))
		(if (ego has: iHairRejuvenator)
			((= bottle (Actor new:))
				view: 184
				ignoreActors:
				illegalBits: 0
				setLoop: 4
				setCycle: Walk
				setStep: 3 9
				posn: 111 1111
				init:
				setScript: bottleScript
			)
		)
		(ego posn: 164 223 setPri: 15 init:)
		(self setScript: rm82Script)
	)
)

(instance rm82Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (== state 16) (ShakeScreen 1 (Random 1 3)))
		(cond 
			(
			(and (& (ego onControl:) $0008) (== currentStatus egoNormal)) (ego setPri: 1) (self changeState: 4))
			(
			(and (& (ego onControl:) $0004) (== currentStatus egoNormal)) (self changeState: 19))
			((and debugging (== currentStatus egoNormal)) (= oldEgoX (ego x?)) (= oldEgoY (ego y?)))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 5))
			(1
				(HandsOff)
				(ego
					illegalBits: 0
					setLoop: 3
					setMotion: MoveTo 164 180 self
				)
			)
			(2
				(ego setPri: 13 setMotion: MoveTo 164 188 self)
			)
			(3
				(NormalEgo 3)
				(ego observeControl: 16384)
			)
			(4
				(HandsOff)
				(Print 82 22 #at -1 20 #dispose)
				(= currentStatus egoFalling)
				(theSound number: 1 play:)
				(ego
					view: 103
					illegalBits: 0
					ignoreActors:
					posn: (ego x?) (- (ego y?) 15)
					cel: 0
					setStep: 1 15
					setCycle: Forward
					setMotion: MoveTo (ego x?) (+ (ego y?) 200) self
				)
			)
			(5
				(cls)
				(= currentStatus egoStopped)
				(Print 82 23)
				(= currentStatus egoDead)
				(if (== debugging TRUE)
					(NormalEgo)
					(ego observeControl: 16384 posn: oldEgoX oldEgoY)
				)
			)
			(6
				(if holdingBomb
					(PrintItIs)
				else
					(bottleScript changeState: 1)
				)
			)
			(7
				(Print 82 24 #at -1 20 #draw)
				(User canInput: TRUE)
				(if (not (ego has: iAirsickBag))
					(HandsOff)
					(Print 82 25)
					(Print 82 26 #at -1 152)
					(= currentStatus egoDead)
				)
			)
			(8
				(if (!= holdingBomb TRUE)
					(-- state)
					(bottleScript changeState: 1)
				else
					(ego view: 184 setLoop: 1 cel: 0 setCycle: EndLoop self)
				)
			)
			(9
				(theGame changeScore: 5)
				(Print 82 27 #at -1 20 #draw)
				(if (not (ego has: iMatches))
					(HandsOff)
					(Print 82 25)
					(Print 82 26 #at -1 152)
					(= currentStatus egoDead)
				else
					(= bombHasWick 1)
					(User canInput: TRUE)
				)
			)
			(10
				(if (!= holdingBomb TRUE)
					(-- state)
					(bottleScript changeState: 1)
				else
					(HandsOff)
					(ego view: 184 setLoop: 2 cel: 0 setCycle: EndLoop self)
					(ego put: iMatches -1)
					(ego put: iAirsickBag -1)
				)
			)
			(11
				(bottle
					setPri: (+ (ego priority?) 1)
					posn: (+ (ego x?) 7) (- (ego y?) 19)
					setLoop: 3
					setCycle: Forward
				)
				(theGame changeScore: 5)
				(Print 82 28 #at -1 20 #draw)
				(Print 82 29 #at -1 152)
				(= bombLit TRUE)
				(User canInput: TRUE)
				(= seconds 5)
			)
			(12
				(HandsOff)
				(= currentStatus egoNoDropBomb)
				(curRoom newRoom: 152)
			)
			(13
				(if (!= holdingBomb TRUE)
					(-- state)
					(bottleScript changeState: 1)
				else
					(if bombLit
						(bottle setLoop: 5)
						(Print 82 30 #at -1 20)
					else
						(Print 82 31 #at -1 20)
					)
					(ego
						setMotion: 0
						view: 184
						setLoop: 2
						setCel: 255
						put: 21 -1
					)
					(bottle
						posn: (+ (ego x?) 7) (- (ego y?) 19)
						setPri: 0
						setMotion: MoveTo 162 124 self
					)
					(= bombHasWick FALSE)
					(= holdingBomb FALSE)
				)
			)
			(14
				(if (not bombLit) (= state 22))
				(= bombLit FALSE)
				(bottle setMotion: MoveTo 162 422 self)
			)
			(15
				(NormalEgo 0)
				(bottle dispose:)
				(theGame changeScore: 10)
				(= seconds 3)
			)
			(16
				(theSound number: 6 play:)
				(Print 82 32 #draw)
				(= bombDropped TRUE)
				(elevatorDoor setCycle: EndLoop self)
			)
			(17
				(elevatorDoor stopUpd:)
				(= seconds 2)
			)
			(18 (Print 82 33 #draw))
			(19
				(HandsOff)
				(= currentStatus egoWonGame)
				(ego setLoop: 2 setPri: 10)
				(= seconds 3)
			)
			(20
				(Print 82 34 #at -1 20)
				(Print 82 35 #at -1 20)
				(Print 82 36 #at -1 20)
				(Print 82 22 #at -1 20 #dispose)
				(theSound number: 1 play:)
				(ego
					illegalBits: 0
					ignoreActors:
					setStep: 1 15
					setMotion: MoveTo (ego x?) (+ (ego y?) 200) self
				)
			)
			(21
				(cls)
				(curRoom newRoom: 83)
			)
			(22
				(= currentStatus egoNoWick)
				(HandsOff)
				(Print 82 37)
				(Print 82 38)
				(Print 82 39)
				(curRoom newRoom: 152)
			)
			(23
				(Print 82 40)
				(Print 82 41)
				(= currentStatus egoDead)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/canyon,hole,boulder')
				(Print 82 0)
				(Print 82 1 #at -1 152)
			)
			(if (Said '/glacier') (Print 82 2))
			(if (Said '/door,elevator,box,button,control,burn')
				(if bombDropped (Print 82 3) else (Print 82 4))
			)
			(if (Said '/carpet') (Print 82 5))
			(if (Said '/cloud') (Print 82 6))
			(if (Said '[/airport,<,/,/]')
				(Print 82 7)
				(Print 82 8 #at -1 152)
			)
		)
		(if (Said 'apply/>,beach') (Print 82 9))
		(if (Said 'apply,make/bomb') (Print 82 10))
		(if
		(Said 'jerk,bang,apply,open/door,elevator,button')
			(Print 82 11)
		)
		(if (Said 'board,climb,apply,board/canyon,hole')
			(Print 82 12)
		)
		(if
		(and (ego has: iHairRejuvenator) (Said 'crap,drain/rejuvenator'))
			(Print 82 13)
			(Print 82 14 #at -1 152)
			(ego put: iHairRejuvenator -1)
		)
		(if (Said 'conceal,apply,throw/rejuvenator,bottle')
			(cond 
				((not (ego has: iHairRejuvenator)) (PrintDontHaveIt))
				(
				(and (!= currentStatus egoNormal) (!= currentStatus egoHoldingBottle)) (PrintNotNow))
				((not (& (ego onControl:) $2000)) (Print 82 15))
				(else (PrintOk) (self changeState: 13))
			)
		)
		(if
		(Said 'stick,conceal,jerk,conceal/rejuvenator,bottle/bag')
			(cond 
				(
				(and (not (ego has: iHairRejuvenator)) (not (ego has: iAirsickBag))) (PrintDontHaveIt))
				(
				(and (!= currentStatus egoNormal) (!= currentStatus egoHoldingBottle)) (PrintNotNow))
				(bombHasWick (Print 82 16))
				(else (Print 82 17) (Print 82 18))
			)
		)
		(if
		(Said 'stick,jerk,conceal,conceal/bag/rejuvenator,bottle')
			(cond 
				(
				(and (not (ego has: iHairRejuvenator)) (not (ego has: iAirsickBag))) (PrintDontHaveIt))
				(
				(and (!= currentStatus egoNormal) (!= currentStatus egoHoldingBottle)) (PrintNotNow))
				((not (& (ego onControl:) $2000)) (Print 82 15))
				(bombHasWick (PrintItIs))
				(else (PrintOk) (self changeState: 8))
			)
		)
		(if
			(or
				(Said 'conceal,conceal/match/bottle')
				(Said 'apply,burn/match')
				(Said 'burn,burn/bottle,rejuvenator')
			)
			(cond 
				((not (ego has: iMatches)) (Print 82 19))
				((not (ego has: iHairRejuvenator)) (Print 82 20))
				(
				(and (!= currentStatus egoNormal) (!= currentStatus egoHoldingBottle)) (PrintNotNow))
				((!= bombHasWick TRUE) (self changeState: 22))
				(else (PrintOk) (self changeState: 10))
			)
		)
		(if (Said 'burn,burn/bag')
			(cond 
				((not (ego has: iAirsickBag)) (PrintDontHaveIt))
				((not (ego has: iMatches)) (Print 82 19))
				(
				(and (!= currentStatus egoNormal) (!= currentStatus egoHoldingBottle)) (PrintNotNow))
				((!= bombHasWick TRUE) (Print 82 21) (ego put: iMatches -1) (ego put: iAirsickBag -1))
				((not (& (ego onControl:) $2000)) (Print 82 15))
				(else (PrintOk) (self changeState: 10))
			)
		)
	)
)

(instance bottleScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(= currentStatus egoHoldingBottle)
				(HandsOff)
				(ego setMotion: MoveTo 147 128 self)
			)
			(2
				(ego view: 184 setLoop: 0 cel: 0 setCycle: EndLoop self)
			)
			(3
				(= holdingBomb TRUE)
				(rm82Script cue:)
			)
		)
	)
)
