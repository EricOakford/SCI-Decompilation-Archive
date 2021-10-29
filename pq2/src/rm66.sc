;;; Sierra Script 1.0 - (do not remove this comment)
(script# 66)
(include sci.sh)
(use Main)
(use Intrface)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm66 0
)
(synonyms
	(mask glasses)
	(bottle tank)
)

(local
	[oxygenStr 100]
	storedInventory
	local101
	gotTank
	belt
	vest
	wetSuit
	goggles
	fins
	tank1
	tank2
	tank3
	tank4
	askedWhichTank
	local113
)
(instance rm66 of Room
	(properties
		picture 666
		style $0002
	)
	
	(method (init)
		(Load rsVIEW 96)
		(Load rsVIEW 161)
		(super init:)
		(self setLocales: 153)
		(HandsOff)
		(User canInput: 1)
		((= tank3 (View new:))
			view: 96
			loop: 2
			cel: (if (not (== gotTank 3)) 3 else 4)
			posn: 112 101
			init:
			stopUpd:
		)
		((= tank1 (View new:))
			view: 96
			loop: 2
			cel: (if (not (== gotTank 1)) 1 else 4)
			posn: 133 101
			init:
			stopUpd:
		)
		((= tank2 (View new:))
			view: 96
			loop: 2
			cel: (if (not (== gotTank 2)) 2 else 4)
			posn: 154 101
			init:
			stopUpd:
		)
		((= belt (View new:))
			view: 96
			loop: 0
			cel: 0
			posn: 106 149
			init:
			stopUpd:
		)
		((= vest (View new:))
			view: 96
			loop: 3
			cel: 0
			posn: 76 132
			init:
			stopUpd:
		)
		((= wetSuit (View new:))
			view: 96
			loop: 0
			cel: 1
			posn: 210 125
			init:
			stopUpd:
		)
		((= goggles (View new:))
			view: 96
			loop: 1
			cel: 0
			posn: 141 134
			init:
			stopUpd:
		)
		((= fins (View new:))
			view: 96
			loop: 1
			cel: 2
			setPri: 14
			posn: 228 139
			init:
			stopUpd:
		)
		((View new:)
			view: 96
			loop: 1
			cel: 1
			posn: 194 144
			init:
			addToPic:
		)
		((= tank4 (View new:))
			view: 96
			loop: 2
			cel: 0
			posn: 175 101
			init:
			stopUpd:
		)
		((View new:)
			view: 96
			loop: 2
			cel: 4
			posn: 196 101
			setPri: 8
			init:
			stopUpd:
			addToPic:
		)
		(self setScript: rm66Script)
	)
)

(instance rm66Script of Script
	(properties)
	
	(method (doit)
		(if (> local113 1) (-- local113))
		(if (== local113 1)
			(= local113 0)
			(airScript changeState: 1)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 0)
			(1
				(Print 66 0 #at -1 20)
				(= storedInventory 0)
				(while (<= storedInventory 37)
					(if (ego has: storedInventory)
						(PutInRoom storedInventory)
					)
					(++ storedInventory)
				)
				(if (== correctScubaTank gotTank)
					(= scubaTankOxygen 2200)
				else
					(= scubaTankOxygen (+ 243 (* 13 gotTank)))
				)
				(curRoom newRoom: 60)
			)
		)
	)
	
	(method (handleEvent event &tmp temp0 [temp1 100])
		(switch (event type?)
			(evKEYBOARD
				(if
					(or
						(== (= temp0 (event message?)) KEY_F6)
						(== temp0 KEY_F8)
						(== temp0 KEY_F10)
					)
					(Print 66 1)
					(event claimed: 1)
				)
			)
			(evSAID
				(cond 
					(
						(Said
							'[check,look]/air,pressure,(supply[<air]),(bottle[<air]),(gauge[<air,pressure])'
						)
						(if (Btst 3)
							(airScript changeState: 0)
						else
							(Print 66 2 #at -1 145)
						)
					)
					((Said 'look>')
						(cond 
							((Said '[<around,in,in][/van,chamber,equipment]')
								(Print 66 3 #at -1 145)
								(Print
									(Format
										@temp1
										66
										4
										(if (Btst 4) {} else {a wet suit,})
										(if (Btst 6) {} else {a weight belt,})
										(if (Btst 7) {} else {a bouyancy control vest,})
										(if (Btst 8) {} else {a scuba mask,})
									)
									#at
									-1
									11
								)
							)
							(
								(or
									(Said '/wall,bracket')
									(Said '/bottle[<yellow]')
									(Said '/bottle[<gray]')
									(Said '/bottle[<scuba]')
								)
								(Print 66 5 #at -1 145)
								(Print 66 6)
							)
							((Said '/belt[<weight]') (Print 66 7 #at -1 145))
							(
								(or
									(Said '/bcv[<buoyancy]')
									(Said '/bcv<control<bouyancy')
								)
								(Print 66 8 #at -1 145)
							)
							((Said '/fin') (Print 66 9 #at -1 145))
							((Said '/mask') (Print 66 10 #at -1 10))
							((Said '/suit') (Print 66 11))
							((Said '/pane,out') (Print 66 12))
						)
					)
					((Said 'get/bottle[<yellow,air]')
						(if askedWhichTank
							(Print 66 13 #at -1 145)
						else
							(Print 66 6 #at -1 145)
							(= askedWhichTank 1)
						)
					)
					((or (Said '[get]/1') (Said '[get]/bottle<1'))
						(if (Btst 3)
							(if (== gotTank 1)
								(Print 66 14 #at -1 145)
							else
								(Print 66 15 #at -1 145)
							)
						else
							(Print 66 16 #at -1 145)
							(Bset 3)
							(tank1 setCel: 4)
							(= gotTank 1)
						)
					)
					((or (Said '[get]/2') (Said '[get]/bottle<2'))
						(if (Btst 3)
							(if (== gotTank 2)
								(Print 66 14 #at -1 145)
							else
								(Print 66 15 #at -1 145)
							)
						else
							(Print 66 16 #at -1 145)
							(Bset 3)
							(tank2 setCel: 4)
							(= gotTank 2)
						)
					)
					((or (Said '[get]/3') (Said '[get]/bottle<3'))
						(if (Btst 3)
							(if (== gotTank 3)
								(Print 66 14 #at -1 145)
							else
								(Print 66 15 #at -1 145)
							)
						else
							(Print 66 16 #at -1 145)
							(Bset 3)
							(tank3 setCel: 4)
							(= gotTank 3)
						)
					)
					(
						(or
							(Said 'get/bottle<gray')
							(Said '[get]/bottle<4')
							(Said '[get]/4')
						)
						(tank4 startUpd: cel: 4)
						(Print 66 17 #at -1 140 #draw)
						(tank4 cel: 0 stopUpd:)
					)
					((Said 'get/belt[<weight]')
						(if (Btst 6)
							(Print 66 18 #at -1 145)
						else
							(Print 66 19 #at -1 145)
							(belt hide:)
							(Bset 6)
						)
					)
					((Said 'get/mask')
						(if (Btst 8)
							(Print 66 18 #at -1 145)
						else
							(Print 66 20 #at -1 145)
							(goggles hide:)
							(Bset 8)
						)
					)
					((Said 'get/fin>')
						(cond 
							((Btst 5)
								(if (Said 'get/fin<yellow')
									(Print 66 18 #at -1 145)
								else
									(Print 66 21)
								)
							)
							((Said 'get/fin<black') (Print 66 21))
							(else (Print 66 22 #at -1 145) (fins hide:) (Bset 5))
						)
						(event claimed: 1)
					)
					(
						(or
							(Said 'get/suit<wet')
							(Said 'get/suit[<scuba]')
							(Said 'get/suit[<dive]')
							(Said 'change,(get<off)/cloth')
							(Said 'change<in[/suit]')
						)
						(if (Btst 4)
							(Print 66 23 #at -1 145)
						else
							(Print 66 24 #at -1 145)
							(wetSuit hide:)
							(Bset 4)
						)
					)
					(
						(or
							(Said 'get/bcv<control<buoyancy')
							(Said 'get/bcv[<bouyancy]')
						)
						(if (Btst 7)
							(Print 66 18 #at -1 145)
						else
							(Print 66 25 #at -1 145)
							(vest hide:)
							(Bset 7)
						)
					)
					(
						(or
							(Said 'exit[/van]')
							(Said 'get<out[/van]')
							(Said 'open/door')
							(Said 'swim,dive[<go]')
						)
						(self changeState: 1)
					)
					((Said 'get[/!*,equipment,crud]') (Print 66 26))
					((Said '(deposit<on),wear') (Print 66 27))
					((Said 'replace,deposit/bottle,1,2,3')
						(if gotTank
							(switch gotTank
								(1 (tank1 cel: 1 forceUpd:))
								(2 (tank2 cel: 2 forceUpd:))
								(3 (tank3 cel: 3 forceUpd:))
							)
							(Print 66 28 #at -1 145)
							(= gotTank 0)
							(Bclr 3)
							(= local113 1)
						else
							(Print 66 29 #at -1 145)
						)
					)
					((Said 'replace,deposit/bcv,suit,fin,mask,belt') (Print 66 30))
				)
			)
		)
	)
)

(instance airScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== correctScubaTank gotTank)
					(= scubaTankOxygen 2200)
					(SolvePuzzle 2 74)
				else
					(= scubaTankOxygen (+ 243 (* 13 gotTank)))
				)
				(DrawCel 161 0 0 262 62 15)
				(Format @oxygenStr 66 31 scubaTankOxygen)
				(Display
					@oxygenStr
					dsCOORD
					277
					76
					dsCOLOR
					14
					dsBACKGROUND
					1
				)
				(= local113 300)
			)
			(1
				(DrawCel 161 0 2 262 62 15)
				(Display
					@oxygenStr
					dsCOORD
					277
					76
					dsCOLOR
					0
					dsBACKGROUND
					0
				)
			)
		)
	)
)
