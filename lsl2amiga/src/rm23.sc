;;; Sierra Script 1.0 - (do not remove this comment)
(script# 23)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use Menu)
(use Actor)
(use System)

(public
	rm23 0
)

(local
	local0
	confirmTrashSearch
	trashOnCurb
	garageIsOpen
	printRet
)
(instance rm23 of Room
	(properties
		picture 23
		horizon 107
		north 19
		east 24
	)
	
	(method (init)
		(Load VIEW 253)
		(if (== prevRoomNum 99)
			(self style: IRISOUT)
		)
		(super init:)
		(if (not ((inventory at: iDollarBill) ownedBy: curRoomNum))
			(= garageIsOpen TRUE)
			(addToPics add: aView1 doit:)
			(ego observeControl: cYELLOW)
		else
			(addToPics add: aView2 doit:)
		)
		(addToPics add: aView3 aView4 aView5 aView6 aView7 doit:)
		(if
			(and
				garageIsOpen
				(ego has: iCruiseTicket)
				((inventory at: iPassport) ownedBy: curRoomNum)
			)
			(= trashOnCurb TRUE)
			(addToPics add: aView8 aView9 doit:)
		)
		(cond 
			((== prevRoomNum 19)
				(ego posn: 299 110)
			)
			((!= prevRoomNum 24)
				(TheMenuBar draw:)
				(StatusLine enable:)
				(ego loop: 1 posn: 135 151)
			)
		)
		(NormalEgo)
		(ego init:)
		(self setRegions: CITY setScript: rm23Script)
	)
)

(instance rm23Script of Script
	(method (doit)
		(super doit:)
		(if
			(or
				(& (ego onControl:) cBLUE)
				(& (ego onControl:) cCYAN)
			)
			(ego setPri: 2)
		else
			(ego setPri: -1)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said '(look<in),explore/bra,bra')
			(cond 
				((not ((inventory at: iDollarBill) ownedBy: curRoomNum))
					(AlreadyTook)
				)
				((& (ego onControl:) cBLUE)
					(Ok)
					(Print 23 0)
				)
				(else
					(event claimed: FALSE)
				)
			)
		)
		(if (Said 'look/bra')
			(cond 
				((not ((inventory at: iDollarBill) ownedBy: curRoomNum))
					(Print 23 1)
					(if (> filthLevel 4)
						(Print 23 2 #at -1 130)
					)
				)
				((not (& (ego onControl:) cBLUE))
					(NotClose)
				)
				(else
					(Ok)
					(Print 23 0)
				)
			)
		)
		(if (Said 'get/buck,bill,(bill<buck)')
			(cond 
				((not ((inventory at: iDollarBill) ownedBy: curRoomNum))
					(AlreadyTook)
				)
				((not (& (ego onControl:) cBLUE))
					(NotClose)
				)
				(else
					(Ok)
					(Print 23 3)
					(ego get: iDollarBill)
					(theGame changeScore: 3)
				)
			)
		)
		(if (Said 'get/bra')
			(Print 23 4)
		)
		(if
			(or
				(Said 'explore,look,board/barrel,barrel,(barrel<barrel)')
				(and confirmTrashSearch (Said 'ok,i'))
			)
			(cond 
				((not trashOnCurb)
					(Print 23 5)
				)
				((not (ego inRect: 81 148 144 172))
					(NotClose)
				)
				(((inventory at: iPassport) ownedBy: curRoomNum)
					(if (not confirmTrashSearch)
						(= confirmTrashSearch TRUE)
						(Print 23 6)
					else
						(Print 23 7)
						(Print 23 8)
					)
				)
				(else
					(Print 23 9)
				)
			)
		)
		;Amiga addition
		(if (Said 'women/eat')
			(if (not debugging)
				(event claimed: FALSE)
				(return)
			)
			(switch
				(= printRet
					(Print 23 10
						#mode teJustCenter
						#title {Cheater!}
						#icon 836 0 1
						#button {Ship} 1
						#button {Resort} 2
						#button {Airport} 3
						#button {Airplane} 4
						#button {Island} 5
					)
				)
				(1
					(ego get:
						iSwimsuit
						iWadODough
						iPassport
						iGrotesqueGulp
						iSunscreen
						iGrotesqueGulp
					)
					(Animate (cast elements?) FALSE)
					(^= debugging TRUE)
					(curRoom newRoom: 31)
				)
				(2
					(ego get:
						iWadODough
						iPassport
						iOnklunk
						iBikiniTop
					)
					(Animate (cast elements?) FALSE)
					(^= debugging TRUE)
					(curRoom newRoom: 42)
				)
				(3
					(ego get:
						iWadODough
						iPassport
						iFlower
						iMatches
						iKnife
					)
					(= currentEgoView 149)
					(Animate (cast elements?) FALSE)
					(^= debugging TRUE)
					(curRoom newRoom: 50)
				)
				(4
					(ego get:
						iWadODough
						iPassport
						iKnife
						iMatches
						iPamphlet
						iHairRejuvenator
						iAirlineTicket
						iParachute
						iBobbyPin
					)
					(Animate (cast elements?) FALSE)
					(^= debugging TRUE)
					(curRoom newRoom: 58)
				)
				(5
					(ego get:
						iMatches
						iKnife
						iHairRejuvenator
						iAirsickBag
					)
					(Animate (cast elements?) FALSE)
					(^= debugging TRUE)
					(curRoom newRoom: 70)
				)
			)
		)
		(if (Said '/balcony')
			(Print 23 11)
		)
		(if (Said 'board,open,(board<in)/building')
			(Print 23 12)
		)
		(if
			(Said 'get/(barstool<duty),cosmo,barrel,barrel,mower,rack,barstool,buffet')
			(Print 23 13)
		)
		(if (Said 'break/cup')
			(Print 23 14)
			(Print 23 15 #at -1 130)
		)
		(if (Said 'close,open,close,unbolt,bolt/door')
			(Print 23 16)
		)
		(if (Said '/flower')
			(Print 23 17)
		)
		(if (Said 'look,(look<in),(look<through),open>')
			(if (and trashOnCurb (Said '/barrel,barrel,(barrel<barrel)'))
				(Print 23 18)
			)
			(if (Said '/cup')
				(Print 23 19)
			)
			(if (Said '/door<garage')
				(Print 23 20)
			)
			(if (Said '/door')
				(Print 23 21)
			)
		)
		(if (Said 'explore,look>')
			(if (Said '/pole,sign')
				(Print 23 22)
			)
			(if (Said '/(barstool<duty),mower,rack,barstool,buffet')
				(Print 23 23)
			)
			(if (Said '/carpet,carpet')
				(Print 23 24)
			)
			(if (Said '/art')
				(Print 23 25)
			)
			(if (Said '[/airport,carpet,building,garage]')
				(cond 
					((& (ego onControl:) cCYAN)
						(Print 23 26)
					)
					((not (& (ego onControl:) cBLUE))
						(Print 23 27)
					)
					(((inventory at: iDollarBill) ownedBy: curRoomNum)
						(Print 23 28)
					)
					(else
						(Print 23 29)
					)
				)
			)
		)
		(if (Said 'get/passport')
			(cond 
				((not trashOnCurb)
					(Print 23 5)
				)
				((not (ego inRect: 81 148 144 172))
					(NotClose)
				)
				(((inventory at: iPassport) ownedBy: curRoomNum)
					(Ok)
					(ego get: iPassport)
					(theGame changeScore: 5)
					(Print 23 30)
				)
				(else
					(AlreadyTook)
				)
			)
		)
	)
)

(instance aView1 of PicView
	(properties
		y 135
		x 93
		view 253
		cel 1
		priority 9
	)
)

(instance aView2 of PicView
	(properties
		y 130
		x 95
		view 253
		priority 1
		signal ignrAct
	)
)

(instance aView3 of PicView
	(properties
		y 139
		x 136
		view 253
		cel 2
		priority 9
		signal ignrAct
	)
)

(instance aView4 of PicView
	(properties
		y 138
		x 49
		view 253
		cel 2
		priority 9
	)
)

(instance aView5 of PicView
	(properties
		y 143
		x 35
		view 253
		cel 2
		priority 9
	)
)

(instance aView6 of PicView
	(properties
		y 110
		x 149
		view 253
		cel 3
		priority 13
	)
)

(instance aView7 of PicView
	(properties
		y 135
		x 15
		view 253
		cel 1
		priority 1
	)
)

(instance aView8 of PicView
	(properties
		y 167
		x 101
		view 253
		loop 1
		priority 12
	)
)

(instance aView9 of PicView
	(properties
		y 167
		x 122
		view 253
		loop 1
		cel 1
		priority 12
	)
)
