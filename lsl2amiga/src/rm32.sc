;;; Sierra Script 1.0 - (do not remove this comment)
(script# 32)
(include game.sh)
(use Main)
(use Door)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm32 0
)

(local
	egoX
	egoY
	local2
)
(instance theSound of Sound)

(instance rm32 of Room
	(properties
		picture 32
	)
	
	(method (init)
		(Load VIEW 304)
		(Load VIEW 131)
		(if (ego has: iSwimsuit)
			(Load VIEW 132)
		)
		(super init:)
		(NormalEgo)
		(self setRegions: SHIP setScript: rm32Script)
		(if ((inventory at: iFruit) ownedBy: curRoomNum)
			(aFruit
				init:
			)
		)
		(aPorthole
			setCycle: Forward
			cycleSpeed: 5
			isExtra: TRUE
			init:
		)
		(if metMama
			(Load VIEW 309)
			(Load SOUND 6)
			(theSound number: 6 init:)
			(aMama
				illegalBits: 0
				ignoreActors:
				setStep: 3 2
				setCycle: Forward
				init:
			)
			(if (and (not rgSeconds) (== gameState rgSHIP))
				(= currentStatus egoSTOPPED)
				(rm32Script changeState: 1)
			)
		)
		(aDoor
			entranceTo: 33
			doorCtrl: cGREEN
			roomCtrl: cCYAN
			msgLook: {This door should be locked, as it leads to someone else's cabin. (But, it's not!)}
			msgFunny: {You may have heard a soft, "Come in." (But you're not sure.)}
			msgCloser: {Just walk near it.}
			init:
		)
		(if (== prevRoomNum 33)
			(ego posn: 200 113)
		else
			(ego posn: 156 121)
		)
		(ego init:)
	)
)

(instance rm32Script of Script
	(method (doit)
		(super doit:)
		(if (== state 20)
			(ShakeScreen 1 (Random 1 3))
		)
		(if (& (ego onControl:) cBLUE)
			(curRoom newRoom: 31)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(= currentStatus egoSTOPPED)
				(= local2 2)
				(HandsOff)
				(= cycles 5)
			)
			(2
				(Print 32 30)
				(ego setMotion: MoveTo 159 112 self)
			)
			(3
				(ego setMotion: MoveTo 155 103 self)
			)
			(4
				(= currentStatus egoSLEEPING)
				(ego
					view: 131
					setLoop: (if (== currentEgoView vEgo) 0 else 2)
					cel: 0
					setCycle: Forward
					cycleSpeed: 2
					setPri: 9
					illegalBits: 0
					posn: 124 98
					setMotion: 0
				)
				(= seconds 5)
			)
			(5
				(= currentStatus egoSTOPPED)
				(HandsOff)
				(= seconds 5)
			)
			(6
				(if (!= (aDoor doorState?) doorClosed)
					(self changeState: 7)
				else
					(Print 32 31)
					(aDoor locked: 0 notify: self force: 1 open:)
				)
			)
			(7
				(Print 32 32)
				(= seconds 2)
			)
			(8
				(aMama show: setMotion: MoveTo 181 111 self)
			)
			(9
				(aMama setLoop: 1 setCel: 0 posn: 177 110 setPri: 13)
				(= cycles 2)
			)
			(10
				(aMama posn: 171 108 setCel: 1)
				(= cycles 2)
			)
			(11
				(aMama posn: 165 106 setCel: 2)
				(= cycles 2)
			)
			(12
				(aMama posn: 162 104 setCel: 3)
				(= cycles 2)
			)
			(13
				(Print 32 33 #at -1 20)
				(aMama setLoop: 2 posn: 162 104 setCel: 0)
				(= cycles 2)
			)
			(14
				(aMama posn: 162 104 setCel: 1)
				(= cycles 2)
			)
			(15
				(aMama posn: 162 104 setCel: 2)
				(= cycles 2)
			)
			(16
				(aMama posn: 160 102 setCel: 3)
				(= cycles 2)
			)
			(17
				(aMama posn: 158 98 setCel: 4)
				(= cycles 2)
			)
			(18
				(aMama posn: 154 92 setCel: 5)
				(= cycles 2)
			)
			(19
				(aMama posn: 158 89 setCel: 5)
				(= cycles 2)
			)
			(20
				(theSound play:)
				(aMama
					setLoop: 3
					setMotion: 0
					setCycle: Forward
					posn: 129 106
				)
				(ego
					setLoop: (if (== currentEgoView vEgo) 1 else 3)
					posn: 125 93
				)
				(= seconds 4)
			)
			(21
				(aMama setLoop: 0 setCel: 0 posn: 155 103)
				(Print 32 34 #at -1 15 #width 280 #draw)
				(Print 32 35 #at -1 15 #width 280)
				(= seconds 1)
			)
			(22
				(= currentStatus egoDYING)
				(if (== local2 2)
					(Print 32 36)
				else
					(Print 32 37 #at -1 15 #width 280)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'open,look/cabinet')
			(if (not (ego inRect: 150 96 168 110))
				(NotClose)
			else
				(Print 32 0)
			)
		)
		(if (Said 'look<on,over,above/cabinet[<(]')
			(Print 32 1)
			(Print 32 2 #at -1 130)
		)
		(if (Said 'look<below/bed')
			(Print 32 3)
			(Print 32 4)
		)
		(if (Said 'look>')
			(if (Said '/bed')
				(Print 32 5)
				(if (> filthLevel 10)
					(Print 32 6 #at -1 130)
				)
			)
			(if (Said '/new,bureau,buffet')
				(cond 
					((not (ego inRect: 150 96 168 110))
						(NotClose)
					)
					((not ((inventory at: iFruit) ownedBy: curRoomNum))
						(Print 32 7)
					)
					(else
						(Print 32 8)
						(Print 32 9)
						(Print 32 10)
						(if metMama
							(Print 32 11)
						else
							(Print 32 12)
						)
					)
				)
			)
			(if
				(and
					((inventory at: iFruit) ownedBy: curRoomNum)
					(Said '/basket,basket')
				)
				(if (not (ego inRect: 150 96 168 110))
					(NotClose)
				else
					(Print 32 8)
					(Print 32 9)
					(Print 32 10)
					(if metMama
						(Print 32 11)
					else
						(Print 32 12)
					)
				)
			)
			(if (Said '[/cabin,airport]')
				(Print 32 13)
				(Print 32 14)
			)
		)
		(if (Said '(get,climb<in),board,board/cabinet')
			(Print 32 15)
			(Print 32 16 #at -1 130)
		)
		(if (Said 'get/dirt')
			(Print 32 17)
		)
		(if
			(or
				(Said 'get/dress')
				(Said 'get<dress')
				(Said 'wear,alter,(get<off),drain,(conceal<on)/job,bra,bra,bikini')
			)
			(cond 
				((== currentEgoView 132)
					(if (not (ego inRect: 170 90 176 110))
						(Print 32 18)
					else
						(= currentEgoView vEgo)
						(Ok)
						(ego view: vEgo)
					)
				)
				((not (ego has: iSwimsuit))
					(if (ego has: iBikiniTop)
						(Print 32 19)
					else
						(DontHave)
					)
				)
				((!= currentStatus egoNORMAL)
					(NotNow)
				)
				((not metMama)
					(Print 32 20)
				)
				((not (ego inRect: 170 90 176 110))
					(Print 32 18)
				)
				(else
					(= currentEgoView 132)
					(Print 32 21)
					(ego view: 132)
				)
			)
		)
		(if (Said 'open/bureau,new,new')
			(Print 32 22)
			(Print 32 23)
			(Print 32 24)
			(Print 32 25)
			(Print 32 26)
		)
		(if
			(and
				(not (ego has: iFruit))
				((inventory at: iFruit) ownedBy: curRoomNum)
				(Said 'eat,apply/basket')
			)
			(Print 32 27)
			((inventory at: iFruit) moveTo: -1)
			(theGame changeScore: -2)
		)
		(if (Said 'get/basket,basket')
			(cond 
				((not ((inventory at: iFruit) ownedBy: curRoomNum))
					(AlreadyTook)
				)
				((not (ego inRect: 150 96 168 110))
					(NotClose)
				)
				(else
					(Ok)
					(aFruit dispose:)
					(ego get: iFruit)
					(theGame changeScore: 3)
				)
			)
		)
		(if (Said 'lie,board,bath[/bed,nap,barstool]')
			(cond 
				((== (ego view?) 131)
					(YouAre)
				)
				((!= currentStatus egoNORMAL)
					(NotNow)
				)
				((not (ego inRect: 150 96 168 110))
					(NotClose)
				)
				(else
					(= currentStatus egoSLEEPING)
					(Print 32 28)
					(User canControl: FALSE canInput: TRUE)
					(= egoX (ego x?))
					(= egoY (ego y?))
					(= currentEgoView (ego view?))
					(= local2 1)
					(ego
						view: 131
						setLoop: (if (== currentEgoView vEgo) 0 else 2)
						cel: 0
						setCycle: Forward
						cycleSpeed: 2
						setPri: 9
						illegalBits: 0
						posn: 124 98
						setMotion: 0
					)
					(if metMama
						(self changeState: 5)
					)
				)
			)
		)
		(if
			(or
				(Said 'new,get,awaken,new[/up]')
				(Said '(get<off),disembark/bed')
				(Said 'new,get<up')
			)
			(cond 
				((!= (ego view?) 131)
					(Print 32 29)
				)
				((!= currentStatus egoSLEEPING)
					(NotNow)
				)
				(else
					(Ok)
					(ego posn: egoX egoY)
					(NormalEgo 0)
					(if (== (aDoor doorState?) doorClosed)
						(ego observeControl: cYELLOW)
					)
				)
			)
		)
	)
)

(instance aFruit of View
	(properties
		y 83
		x 156
		view 304
		loop 2
		priority 4
	)
)

(instance aPorthole of Prop
	(properties
		y 75
		x 148
		view 304
		loop 1
		priority 4
	)
)

(instance aMama of Actor
	(properties
		y 110
		x 217
		view 309
		cel 3
		priority 7
	)
)

(instance aDoor of Door
	(properties
		y 114
		x 207
		view 304
		priority 7
	)
)
