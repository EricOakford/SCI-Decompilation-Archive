;;; Sierra Script 1.0 - (do not remove this comment)
(script# 16)
(include game.sh)
(use Main)
(use Door)
(use Intrface)
(use Game)
(use Actor)
(use System)

(public
	rm16 0
)

(local
	local0
	aDoor
)
(instance rm16 of Room
	(properties
		picture 16
		horizon 111
		east 17
		south 20
		west 15
	)
	
	(method (init)
		(Load VIEW 224)
		(super init:)
		((View new:)
			view: 224
			loop: 1
			cel: 1
			posn: 150 140
			setPri: 10
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 224
			loop: 1
			cel: 0
			posn: 150 83
			setPri: 15
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 224
			loop: 1
			cel: 2
			posn: 113 117
			setPri: 8
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 224
			loop: 1
			cel: 3
			posn: 299 92
			setPri: 13
			ignoreActors:
			addToPic:
		)
		(cond 
			((== prevRoomNum 0)
				(ego posn: 248 133)
			)
			((== prevRoomNum 12)
				(ego posn: 252 135)
			)
			((== prevRoomNum 20)
				(ego posn: 73 187)
			)
			((== prevRoomNum 116)
				(ego posn: 150 130)
			)
		)
		(NormalEgo)
		(ego init:)
		((= aDoor (AutoDoor new:))
			view: 224
			setLoop: 0
			posn: 136 134
			setPri: 9
			entranceTo: 116
			msgLook: {Through the door you see an exclusive mens clothing store.
			(And, a beautiful female clerk!) A sign says they accept any form of U. S. currency.}
			msgFunny: {Everytime you try to knock, it opens itself!}
			init:
		)
		(self setRegions: CITY setScript: rm16Script)
	)
)

(instance rm16Script of Script
	(method (doit)
		(super doit:)
		(if (& (ego onControl:) cCYAN)
			(curRoom newRoom: 12)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/pole,(sign<freeway)')
				(Print 16 0)
			)
			(if (Said '/cup,sign')
				(Print 16 1)
			)
			(if (Said '/flower')
				(Print 16 2)
			)
			(if (Said '/carpet,(carpet<door)')
				(Print 16 3)
				(Print 16 4 #at -1 130)
			)
			(if (Said '[/building,building,airport]')
				(Print 16 5)
				(if (ego has: iMillionDollarBill)
					(Print 16 6)
				)
				(if (not (ego has: iWadODough))
					(Print 16 7)
				)
			)
		)
	)
)
