;;; Sierra Script 1.0 - (do not remove this comment)
(script# TROLL)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)

(public
	rgTroll 0
)

(procedure (TrollSays)
	(cls)
	(Print &rest
		#title {Troll}
		#font 4
		#at 100 18
		#width 200
	)
)

(procedure (NoTrollHere)
	(Print 601 34)
)

(procedure (cls)
	(if modelessDialog
		(modelessDialog dispose:)
	)
)

(procedure (DontHaveThat)
	(Printf 601 35 &rest)
)

(procedure (GiveToTroll what points)
	(if (Btst fTrollBlocksBridge)
		(if (ego has: what)
			(if (NearTroll)
				(Printf 601 36 &rest)
				(theGame changeScore: (if (!= points 0) points else -6))
				(Bclr fTrollBlocksBridge)
				(ego put: what)
				(Bset fTrollPaid)
			else
				(Print 601 37)
			)
		else
			(DontHaveThat &rest)
		)
	else
		(NoTrollHere)
	)
)

(procedure (NearTroll)
	(return (< (ego distanceTo: theMenace) 45))
)

(instance rgTroll of Region
	(properties)
	
	(method (init)
		(Load VIEW 120)
		(self keep: FALSE)
		(super init:)
		(if (Btst fTrollPaid)
			(Bclr fTrollDead)
			(Bclr fTrollPaid)
		)
		(theMenace
			view: 120
			setStep: 6 4
			setCycle: Walk
			setLoop: -1
			setPri: -1
			ignoreHorizon:
			init:
			hide:
		)
		(Bclr fTrollBlocksBridge)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (handleEvent event &tmp i)
		(super handleEvent: event)
		(if
			(and
				(or (== (ego view?) 54) (== (ego view?) 14))
				(== (event type?) saidEvent)
			)
			(if
				(not
					(if (or (Said 'swim/') (Said 'get,take,drink/water,drink'))
					else
						(Said 'fill/bucket')
					)
				)
				(Print 601 0)
				(event claimed: TRUE)
			else
				(event claimed: FALSE)
			)
		)
		(if (or (event claimed?) (!= (event type?) saidEvent))
			(return)
		)
		(cond 
			((or (Said 'look,check/troll,man') (MousedOn theMenace event shiftDown))
				(theMenace doVerb: verbLook)
			)
			((Said 'look,check>')
				(cond 
					((Said '<across[/brook,water,brook]')
						(Print 601 1)
					)
					((Said '/brook,water,brook')
						(Print 601 2)
					)
					((Said '<below,below/bridge')
						(Print 601 3)
					)
					((Said '/bridge')
						(switch curRoomNum
							(39
								(Print 601 4)
							)
							(else 
								(Print 601 5)
								(event claimed: TRUE)
							)
						)
					)
				)
			)
			((Said 'use/dagger')
				(if (ego has: iDagger)
					(Print 601 6)
				else
					(Print 601 7)
				)
			)
			((Said 'attack,kill,shoot/troll,man')
				(if (Btst fTrollBlocksBridge)
					(Print 601 8)
				else
					(NoTrollHere)
				)
			)
			((Said 'kill>')
				(if (Said '/goat')
					(cond 
						((not (Btst fGoatFollows))
							(Print 601 9)
						)
						((ego has: iDagger)
							(Print 601 10)
							(ego put: iDagger)
							(theGame changeScore: -5)
						)
					)
				)
			)
			((Said 'use/shot')
				(if (Btst fTrollBlocksBridge)
					(Print 601 8)
				else
					(Print 601 11)
				)
			)
			((and (not (Btst fTrollBlocksBridge)) (Said '/troll,man'))
				(NoTrollHere)
			)
			((not (Btst fGoatFollows))
				(return)
			)
			((Said 'give>')
				(cond 
					((or (Said '/diamond/troll,man') (Said '/bag[<diamond]/troll,man'))
						(GiveToTroll iPouch
							(if (not (Btst fOpenedPouch)) -3 else 0) 601 12
						)
					)
					((Said '/egg[<gold]/troll,man')
						(GiveToTroll iGoldEgg 0 601 13)
					)
					((Said '/scepter/troll,man')
						(GiveToTroll iSceptre 0 601 14)
					)
					((or (Said '/treasure/troll,man') (Said '/chest[<treasure]/troll,man'))
						(GiveToTroll iChest 0 601 15)
					)
					((Said '/nut<gold/troll,man')
						(if (Btst fOpenedWalnut)
							(GiveToTroll iWalnut 0 601 16)
						else
							(DontHaveThat 601 17)
						)
					)
					((Said '/nut/troll,man')
						(if (Btst fTrollBlocksBridge)
							(if (ego has: iWalnut)
								(if (not (Btst fOpenedWalnut))
									(Print 601 18)
								else
									(GiveToTroll iWalnut 0 601 16)
								)
							else
								(DontHaveThat 601 19)
							)
						else
							(NoTrollHere)
						)
					)
					((Said '/diamond,egg,scepter,treasure,nut')
						(Print 601 20)
					)
					((and (= i (inventory firstTrue: #saidMe)) (i ownedBy: ego))
						(Print 601 21)
					)
				)
			)
			((Said 'pull/troll,man')
				(if (Btst fTrollBlocksBridge)
					(if (NearTroll)
						(Print 601 22)
					else
						(Print 601 23)
					)
				else
					(NoTrollHere)
				)
			)
			((Said 'beg/troll,man')
				(if (Btst fTrollBlocksBridge)
					(if (NearTroll)
						(Print 601 24)
					else
						(Print 601 25)
					)
				else
					(NoTrollHere)
				)
			)
			((Said 'pay/toll,troll,man')
				(TrollSays 601 26)
			)
			((Said 'throw,throw/pebble,boulder,pebble')
				(cond 
					((not (Btst fTrollBlocksBridge))
						(Print 601 27)
					)
					((ego has: iPebbles)
						(Print 601 28)
						(PebbleCount)
					)
					(else
						(Print 601 29)
					)
				)
			)
			((Said 'throw/dagger')
				(if (Btst fTrollBlocksBridge)
					(if (ego has: iDagger)
						(if (NearTroll)
							(Print 601 30)
						else
							(Print 601 31)
						)
						(ego put: iDagger)
						(theGame changeScore: -5)
					else
						(DontHaveThat 601 32)
					)
				else
					(Print 601 33)
				)
			)
		)
	)
)
