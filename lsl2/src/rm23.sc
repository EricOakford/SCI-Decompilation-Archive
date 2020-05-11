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
	local3
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
		(if (== prevRoomNum 99) (self style: 7))
		(super init:)
		(if (not ((inventory at: 1) ownedBy: curRoomNum))
			(= local3 1)
			((View new:)
				view: 253
				loop: 0
				cel: 1
				posn: 93 135
				setPri: 9
				addToPic:
			)
			(ego observeControl: 16384)
		else
			((View new:)
				view: 253
				loop: 0
				cel: 0
				posn: 95 130
				setPri: 1
				ignoreActors:
				addToPic:
			)
		)
		((View new:)
			view: 253
			loop: 0
			cel: 2
			posn: 136 139
			setPri: 9
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 253
			loop: 0
			cel: 2
			posn: 49 138
			setPri: 9
			addToPic:
		)
		((View new:)
			view: 253
			loop: 0
			cel: 2
			posn: 35 143
			setPri: 9
			addToPic:
		)
		((View new:)
			view: 253
			loop: 0
			cel: 3
			posn: 149 110
			setPri: 13
			addToPic:
		)
		((View new:)
			view: 253
			loop: 0
			cel: 1
			posn: 15 135
			setPri: 1
			addToPic:
		)
		(if
			(and
				local3
				(ego has: iCruiseTicket)
				((inventory at: iPassport) ownedBy: curRoomNum)
			)
			(= trashOnCurb TRUE)
			((View new:)
				view: 253
				loop: 1
				cel: 0
				posn: 101 167
				setPri: 12
				addToPic:
			)
			((View new:)
				view: 253
				loop: 1
				cel: 1
				posn: 122 167
				setPri: 12
				brRight: 128
				addToPic:
			)
		)
		(cond 
			((== prevRoomNum 19) (ego posn: 299 110))
			((!= prevRoomNum 24)
				(TheMenuBar draw:)
				(StatusLine enable:)
				(ego loop: 1 posn: 135 151)
			)
		)
		(NormalEgo)
		(ego init:)
		(self setRegions: 200 setScript: rm23Script)
	)
)

(instance rm23Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(or
				(& (ego onControl:) $0002)
				(& (ego onControl:) $0008)
			)
			(ego setPri: 2)
		else
			(ego setPri: -1)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said '(look<in),explore/bra,bra')
			(cond 
				((not ((inventory at: iDollarBill) ownedBy: curRoomNum)) (PrintAlreadyTookIt))
				((& (ego onControl:) $0002) (PrintOk) (Print 23 0))
				(else (event claimed: FALSE))
			)
		)
		(if (Said 'look/bra')
			(cond 
				((not ((inventory at: iDollarBill) ownedBy: curRoomNum))
					(Print 23 1)
					(if (> filthLevel 4) (Print 23 2 #at -1 152))
				)
				((not (& (ego onControl:) $0002)) (PrintNotCloseEnough))
				(else (PrintOk) (Print 23 0))
			)
		)
		(if (Said 'get/buck,bill,(bill<buck)')
			(cond 
				((not ((inventory at: iDollarBill) ownedBy: curRoomNum)) (PrintAlreadyTookIt))
				((not (& (ego onControl:) $0002)) (PrintNotCloseEnough))
				(else
					(PrintOk)
					(Print 23 3)
					(ego get: iDollarBill)
					(theGame changeScore: 3)
				)
			)
		)
		(if (Said 'get/bra') (Print 23 4))
		(if
			(or
				(Said 'explore,look,board/barrel,barrel,(barrel<barrel)')
				(and confirmTrashSearch (Said 'ok,i'))
			)
			(cond 
				((not trashOnCurb) (Print 23 5))
				((not (ego inRect: 81 148 144 172)) (PrintNotCloseEnough))
				(((inventory at: iPassport) ownedBy: curRoomNum)
					(if (not confirmTrashSearch)
						(= confirmTrashSearch TRUE)
						(Print 23 6)
					else
						(Print 23 7)
						(Print 23 8)
					)
				)
				(else (Print 23 9))
			)
		)
		(if (Said '/balcony') (Print 23 10))
		(if (Said 'board,open,(board<in)/building')
			(Print 23 11)
		)
		(if
			(Said
				'get/(barstool<duty),cosmo,barrel,barrel,mower,rack,barstool,buffet'
			)
			(Print 23 12)
		)
		(if (Said 'break/cup')
			(Print 23 13)
			(Print 23 14 #at -1 152)
		)
		(if (Said 'close,open,close,unbolt,bolt/door')
			(Print 23 15)
		)
		(if (Said '/flower') (Print 23 16))
		(if (Said 'look,(look<in),(look<through),open>')
			(if
			(and trashOnCurb (Said '/barrel,barrel,(barrel<barrel)'))
				(Print 23 17)
			)
			(if (Said '/cup') (Print 23 18))
			(if (Said '/door<garage') (Print 23 19))
			(if (Said '/door') (Print 23 20))
		)
		(if (Said 'explore,look>')
			(if (Said '/pole,sign') (Print 23 21))
			(if
			(Said '/(barstool<duty),mower,rack,barstool,buffet')
				(Print 23 22)
			)
			(if (Said '/carpet,carpet') (Print 23 23))
			(if (Said '/art') (Print 23 24))
			(if (Said '[/airport,carpet,building,garage]')
				(cond 
					((& (ego onControl:) $0008) (Print 23 25))
					((not (& (ego onControl:) $0002)) (Print 23 26))
					(((inventory at: iDollarBill) ownedBy: curRoomNum) (Print 23 27))
					(else (Print 23 28))
				)
			)
		)
		(if (Said 'get/passport')
			(cond 
				((not trashOnCurb) (Print 23 5))
				((not (ego inRect: 81 148 144 172)) (PrintNotCloseEnough))
				(((inventory at: iPassport) ownedBy: curRoomNum)
					(PrintOk)
					(ego get: iPassport)
					(theGame changeScore: 5)
					(Print 23 29)
				)
				(else (PrintAlreadyTookIt))
			)
		)
	)
)
