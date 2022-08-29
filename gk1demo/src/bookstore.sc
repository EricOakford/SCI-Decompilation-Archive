;;; Sierra Script 1.0 - (do not remove this comment)
(script# 210)
(include sci.sh)
(use Main)
(use Procs)
(use GKEgo)
(use RoomTeller)
(use Inset)
(use RangeOsc)
(use Osc)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use StopWalk)
(use Timer)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	bookstore 0
	graceProp 1
	gabsMug 2
	coffeePot 3
	graceTeller 4
	graceDoStuff 5
	gabsCoat 6
	graceActor 7
	shopDoor 8
)

(local
	theMagnifyingGlass
	local1 =  1
	local2 =  1
	local3 =  1
	local4
	theGameSetCursor
	local6
	local7
	registerOpen
	local9
	local10
)
(instance bookstore of Rm
	(properties
		noun 2
		picture 210
		style $000a
	)
	
	(method (init)
		(super init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						100
						91
						90
						103
						51
						118
						20
						132
						61
						142
						68
						143
						101
						130
						170
						144
						225
						144
						209
						155
						212
						174
						224
						174
						247
						144
						319
						144
						319
						105
						273
						105
						281
						114
						275
						123
						242
						122
						228
						131
						194
						128
						182
						119
						202
						103
						117
						87
						109
						92
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 124 103 106 95 121 89 143 95 125 103
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 57 122 70 126 54 138 31 130 41 124 50 124
					yourself:
				)
		)
		(ScriptID 920)
		(ScriptID 51)
		(Load rsSCRIPT 211)
		(Load rsMESSAGE 210)
		(User canControl: 1 canInput: 1)
		(ego
			view: 901
			loop: 0
			cel: 0
			setCycle: StopWalk -1
			init:
			state: 2
			ignoreControl: -32768
			ignoreActors: 1
			signal: 4096
		)
		(coatRack init:)
		(coffeeStuff init:)
		(booksBehindLadder init:)
		(booksLeftOfLadder init:)
		(curtainDoor init:)
		(drawers init:)
		(gargoyle init:)
		(germanBooks init:)
		(ladder init:)
		(balcony init:)
		(cashRegister init:)
		(coffeeTable init:)
		(booksBottomRight init:)
		(booksBottomLeft init:)
		(coffeeBooks init:)
		(bookRack init:)
		(windowBooks init:)
		(chandelier init:)
		(rightWindow init:)
		(leftWindow init:)
		(desk init:)
		(doorMat init:)
		(deskLamp init:)
		(snakeBooks init:)
		(deskTop init:)
		(phone init:)
		(coffeePot init:)
		(gracesCoat init:)
		(painting init:)
		(if (not (== currentDay 5)) (gabsCoat init:))
		(newsPaper init: setPri: 11)
		(if (not (ego has: 4))
			(magnifyingGlass init: setPri: 11)
		)
		(if (not (ego has: 3)) (tweezers init: setPri: 11))
		(gabsMug init: setPri: 12)
		(if (== currentDay 11)
			(chair init: x: 260 y: 114 setPri: 8 cel: 10)
		else
			(chair init:)
		)
		(ashTray init:)
		(artSupplies init:)
		(shopDoor init: stopUpd:)
		(if (== currentDay 11)
			(maliasNote init:)
			(Load rsVIEW 2161)
		)
		(walkHandler add: balcony)
		(cond 
			((== prevRoomNum 50)
				(= interrogateSubject 0)
				(ego x: 245 y: 133 cel: 6)
				(graceProp init: setScript: graceDoStuff)
				(graceTeller init: graceProp)
				(cond 
					((Btst 15) (curRoom setScript: timeToGoHome))
					((Btst 55) (ego setScript: getNumFromGrace))
					(else (graceProp setScript: graceDoStuff))
				)
			)
			((== prevRoomNum 370)
				(if (not (Btst 10))
					(Bset 6)
					(switch currentDay
						(1
							(curRoom setScript: (ScriptID 211 1))
							(Bset 8)
							(= local1 0)
						)
						(2
							(curRoom setScript: (ScriptID 211 2))
							(= local1 0)
						)
						(3
							(curRoom setScript: (ScriptID 211 3))
							(= local1 0)
						)
						(4
							(curRoom setScript: (ScriptID 211 4))
							(= local1 0)
						)
						(5
							(curRoom setScript: (ScriptID 211 5))
							(= local1 0)
						)
						(6
							(curRoom setScript: (ScriptID 211 6))
						)
						(7
							(curRoom setScript: startOfDay7)
						)
						(11 (Bclr 6))
					)
				else
					(Bset 6)
					(graceProp init: setScript: graceDoStuff)
					(ego setScript: (ScriptID 211 7))
					(graceTeller init: graceProp)
				)
			)
			((== prevRoomNum 200)
				(if (and (== currentDay 5) (Btst 95))
					(ego setScript: graceGetsTheScale)
					(Bset 6)
				else
					(Bset 6)
					(graceProp init: setScript: graceDoStuff)
					(ego setScript: (ScriptID 211 7))
					(graceTeller init: graceProp)
				)
			)
			(else
				(Bset 8)
				(ego posn: 160 130)
				(graceProp init: setScript: graceDoStuff)
				(graceTeller init: graceProp)
				(Bset 6)
			)
		)
		(if (and (not isDemo) (== currentDay 1))
			(entranceTimer setReal: self 20)
		)
		(if
		(and (>= currentDay 5) (Btst 95) (not (ego has: 20)))
			(= local4 1)
		)
		(if (not (== prevRoomNum 50))
			(theMusic1
				number: 210
				loop: -1
				play:
				setVol: 0
				fade: 80 25 10 0
			)
		)
	)
	
	(method (dispose)
		(if (== prevRoomNum 50) (theMusic1 fade:))
		(walkHandler delete: balcony)
		(entranceTimer client: 0)
		(entranceTimer dispose: delete:)
		(super dispose: &rest)
		(DisposeScript 939)
		(DisposeScript 956)
		(DisposeScript 920)
		(DisposeScript 51)
		(DisposeScript 938)
	)
	
	(method (cue)
		(if (not isDemo)
			(if (not (if (ego script?) else (self script?)))
				(theGame handsOff:)
				(self setScript: enterTheFlorist)
			else
				(entranceTimer setReal: self 20)
			)
		)
	)
)

(instance entranceTimer of Timer
	(properties)
)

(instance graceGetOffTheLadder of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(chair init:)
				(graceActor
					init:
					posn: 173 99
					view: 207
					loop: 1
					cel: 6
					setCycle: Beg self
				)
			)
			(1
				(graceActor loop: 0 cel: 12 setCycle: Beg self)
			)
			(2
				(graceActor
					view: 223
					posn: 180 97
					ignoreActors: 1
					setSpeed: 8
					setStep: 3 2
					setCycle: Walk
					setMotion: PolyPath 251 115 self
				)
			)
			(3
				(graceActor
					view: 208
					loop: 4
					cel: 0
					setCycle: CT 2 1 self
				)
			)
			(4
				(graceActor loop: 4 cel: 3 setCycle: End self)
			)
			(5
				(graceActor dispose:)
				(graceProp init: setScript: graceDoStuff)
				(self dispose:)
			)
		)
	)
)

(instance putCoatOn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 93 96 self)
				(LoadMany 128 219 900)
				(UnLoad 128 211)
			)
			(1
				(Load rsAUDIO 217)
				(Face ego gabsCoat self)
			)
			(2
				(gabsCoat dispose:)
				(ego view: 219 loop: 0 cel: 0 setCycle: CT 3 1 self)
			)
			(3
				(ego loop: 0 cel: 4 setCycle: End self)
				(theSound1 number: 217 play:)
			)
			(4
				(ego normalize: 1 900)
				(Face ego graceProp self)
			)
			(5
				(messager say: 38 6 23 (Random 1 5) self)
			)
			(6
				(messager say: 38 6 24 (Random 1 5) self)
			)
			(7 (Face ego shopDoor self))
			(8
				(shopDoor dispose:)
				(ego
					view: 219
					loop: 1
					cel: 0
					cycleSpeed: 6
					setCycle: CT 1 1 self
				)
			)
			(9
				(theSound1 number: 373 play:)
				(ego
					view: 219
					loop: 1
					cel: 2
					cycleSpeed: 6
					setCycle: CT 8 1 self
				)
			)
			(10
				(ego
					view: 219
					loop: 1
					cel: 9
					cycleSpeed: 6
					setCycle: End self
				)
			)
			(11
				(theSound2 number: 374 play:)
				(ego hide:)
				(shopDoor init:)
				(= cycles 1)
			)
			(12
				(theMusic1 fade:)
				(ego normalize:)
				(ego hide:)
				(curRoom newRoom: 200)
				(self dispose:)
			)
		)
	)
)

(instance getSomeCoffee of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local1 0)
				(if
				(not (if (== (ego x?) 137) (== (ego y?) 138)))
					(ego setMotion: PolyPath 137 138 self)
				else
					(self cue:)
				)
			)
			(1
				(ego
					view: 215
					posn: 81 139
					loop: 0
					cel: 0
					setSpeed: 10
					setCycle: CT 2 1 self
				)
			)
			(2
				(gabsMug dispose:)
				(= cycles 1)
			)
			(3
				(ego view: 215 loop: 0 cel: 3 setCycle: End self)
			)
			(4
				(ego view: 215 loop: 1 cel: 0 setCycle: CT 4 1 self)
			)
			(5
				(coffeePot hide:)
				(ego view: 215 loop: 1 cel: 5 setCycle: End self)
				(theSound1 number: 214 play:)
			)
			(6
				(ego view: 215 loop: 2 setCycle: End self)
			)
			(7
				(coffeePot show:)
				(= seconds 3)
			)
			(8
				(ego view: 215 loop: 3 setCycle: End self)
			)
			(9 (ego setCycle: Beg self))
			(10
				(messager say: 23 12 12 1 self)
			)
			(11
				(ego view: 215 loop: 4 cel: 0 setCycle: CT 3 1 self)
			)
			(12
				(gabsMug posn: 86 120 setPri: 14 init:)
				(ego view: 215 loop: 4 cel: 4 setCycle: End self)
			)
			(13
				(theGame handsOn:)
				(GKEgo normalize: 0 901)
				(ego ignoreActors: 1 ignoreControl: -32768)
				(self dispose:)
			)
		)
	)
)

(instance getTheGermanBook of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if
				(not (if (== (ego x?) 136) (== (ego y?) 93)))
					(ego setMotion: PolyPath 136 93 self)
				else
					(= cycles 1)
				)
			)
			(1
				(ego view: 2152 loop: 1 cel: 0 setCycle: End self)
			)
			(2
				(if (Btst 6)
					(messager say: 12 12 3 1 self)
				else
					(messager say: 12 12 6 1 self)
				)
			)
			(3
				(ego loop: 2 cel: 0 setCycle: CT 3 1 self)
			)
			(4
				(ego loop: 2 cel: 4 setCycle: End self)
				(theSound1 number: 218 play:)
			)
			(5
				(messager say: 12 12 7 0 self)
			)
			(6
				(cond 
					((not (Btst 8)) (= cycles 1))
					((Btst 6) (messager say: 12 12 3 3 self))
					(else (= cycles 1))
				)
			)
			(7
				(if (not (Btst 8))
					(= cycles 1)
				else
					(if (and (Btst 6) (Btst 7))
						(messager say: 12 12 5 2 self)
					)
					(if (and (Btst 6) (not (Btst 7)))
						(messager say: 12 12 4 2 self)
					)
					(if (not (Btst 6)) (messager say: 12 12 6 3 self))
				)
			)
			(8
				(ego loop: 2 cel: 10 setCycle: CT 7 -1 self)
			)
			(9
				(theSound1 number: 218 play:)
				(ego loop: 2 cel: 6 getPoints: 119 1 setCycle: Beg self)
			)
			(10
				(theGame handsOn:)
				(GKEgo normalize: 0 901)
				(ego ignoreActors: 1 ignoreControl: -32768)
				(Bclr 8)
				(self dispose:)
			)
		)
	)
)

(instance getTheNewsPaper of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if
				(not (if (== (ego x?) 137) (== (ego y?) 138)))
					(ego setMotion: PolyPath 137 138 self)
				else
					(self cue:)
				)
			)
			(1
				(newsPaper hide:)
				(ego
					posn: 136 120
					view: 214
					loop: 0
					cel: 0
					setCycle: CT 2 1 self
				)
			)
			(2
				(theSound1 number: 213 play:)
				(ego
					posn: 136 120
					view: 214
					loop: 0
					cel: 3
					setCycle: End self
				)
			)
			(3
				(ego loop: 1 cel: 0 setCycle: End self)
			)
			(4
				(switch currentDay
					(1
						(messager say: 35 12 15 1 3 self)
					)
					(2
						(messager say: 35 12 16 1 self)
					)
					(3
						(messager say: 35 12 17 1 self)
						(Bset 14)
					)
					(4
						(messager say: 35 12 18 1 2 self)
					)
					(5
						(messager say: 35 12 19 1 self)
					)
					(6
						(messager say: 35 12 20 1 6 self)
					)
					(7
						(messager say: 35 12 21 1 2 self)
					)
				)
			)
			(5
				(switch currentDay
					(4 (= cycles 1))
					(6
						(ego loop: 2 cel: 0 setCycle: End self)
					)
					(else 
						(theSound1 number: 213 play:)
						(ego loop: 1 cel: 0 setCycle: Osc 1 self)
					)
				)
			)
			(6
				(switch currentDay
					(1
						(messager say: 35 12 15 4 6 self)
					)
					(2
						(messager say: 35 12 16 2 3 self)
					)
					(3
						(messager say: 35 12 17 2 self)
					)
					(4
						(messager say: 35 12 18 3 8 self)
					)
					(5
						(messager say: 35 12 19 2 3 self)
					)
					(6
						(newsPaper show:)
						(theGame handsOn:)
						(GKEgo normalize: 0 901)
						(ego ignoreActors: 1 ignoreControl: -32768 posn: 137 138)
						(self dispose:)
					)
					(7
						(messager say: 35 12 21 3 self)
					)
				)
			)
			(7
				(switch currentDay
					(1
						(Load rsVIEW 901)
						(theSound1 number: 213 play:)
						(ego loop: 2 cel: 0 setCycle: End self)
					)
					(2
						(theSound1 number: 213 play:)
						(ego loop: 1 cel: 0 setCycle: Osc 1 self)
					)
					(3
						(theSound1 number: 213 play:)
						(ego loop: 1 cel: 0 setCycle: Osc 1 self)
					)
					(4
						(theSound1 number: 213 play:)
						(ego loop: 1 cel: 0 setCycle: Osc 1 self)
					)
					(5
						(ego loop: 2 cel: 0 setCycle: End self)
					)
					(7
						(theSound1 number: 213 play:)
						(ego loop: 1 cel: 0 setCycle: Osc 1 self)
					)
				)
			)
			(8
				(switch currentDay
					(1
						(newsPaper show:)
						(ego
							normalize: 2 901
							ignoreActors: 1
							ignoreControl: -32768
							posn: 137 138
							getPoints: 118 1
						)
						(theGame handsOn:)
						(self dispose:)
					)
					(2
						(messager say: 35 12 16 4 6 self)
					)
					(3
						(messager say: 35 12 17 3 self)
					)
					(4
						(messager say: 35 12 17 9 11 self)
					)
					(5
						(newsPaper show:)
						(theGame handsOn:)
						(GKEgo normalize: 0 901)
						(ego ignoreActors: 1 ignoreControl: -32768 posn: 137 138)
						(self dispose:)
					)
					(7
						(messager say: 35 12 21 4 5 self)
					)
				)
			)
			(9
				(switch currentDay
					(2
						(ego loop: 2 cel: 0 setCycle: End self)
					)
					(3
						(messager say: 35 12 17 4 self)
					)
					(4
						(ego loop: 2 cel: 0 setCycle: End self)
					)
					(7
						(ego loop: 2 cel: 0 setCycle: End self)
					)
				)
			)
			(10
				(switch currentDay
					(2
						(newsPaper show:)
						(theGame handsOn:)
						(GKEgo normalize: 0 901)
						(ego ignoreActors: 1 ignoreControl: -32768 posn: 137 138)
						(self dispose:)
					)
					(3
						(ego loop: 2 cel: 0 setCycle: End self)
					)
					(4
						(newsPaper show:)
						(theGame handsOn:)
						(GKEgo normalize: 0 901)
						(ego ignoreActors: 1 ignoreControl: -32768 posn: 137 138)
						(self dispose:)
					)
					(7
						(newsPaper show:)
						(theGame handsOn:)
						(GKEgo normalize: 0 901)
						(ego ignoreActors: 1 ignoreControl: -32768 posn: 137 138)
						(self dispose:)
					)
				)
			)
			(11
				(newsPaper show:)
				(theGame handsOn:)
				(GKEgo normalize: 0 901)
				(ego ignoreActors: 1 ignoreControl: -32768 posn: 137 138)
				(self dispose:)
			)
		)
	)
)

(instance timeToGoHome of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (graceProp script?) (graceProp setScript: 0))
				(graceProp dispose:)
				(theGame handsOff:)
				(graceActor
					init:
					loop: 0
					cel: 0
					setPri: 8
					ignoreControl: -32768
					setSpeed: 10
					setCycle: End self
				)
				(Bclr 10)
			)
			(1
				(messager say: 1 0 33 1 3 self)
			)
			(2 (= seconds 3))
			(3
				(graceActor loop: 1 cel: 0 setSpeed: 8 setCycle: End self)
			)
			(4
				(messager say: 1 0 33 4 5 self)
			)
			(5
				(graceActor loop: 2 cel: 0 setCycle: End self)
			)
			(6
				(Face ego shopDoor)
				(graceActor
					view: 209
					posn: 203 127
					setLoop: 3
					cel: 3
					setPri: -1
					ignoreActors: 1
					setSpeed: 8
					setStep: 3 2
					setCycle: Fwd
					setMotion: PolyPath 97 97 self
				)
			)
			(7
				(gracesCoat dispose:)
				(graceActor
					view: 2091
					posn: 101 95
					loop: 0
					cel: 0
					setSpeed: 8
					setCycle: CT 3 1 self
				)
			)
			(8
				(theSound1 number: 217 play:)
				(graceActor
					view: 2091
					posn: 101 95
					loop: 0
					cel: 4
					setSpeed: 8
					setCycle: End self
				)
			)
			(9
				(graceActor loop: 1 cel: 0 setCycle: End self)
			)
			(10
				(shopDoor dispose:)
				(theSound1 number: 373 play:)
				(graceActor
					posn: 111 98
					loop: 2
					cel: 0
					setCycle: End self
				)
			)
			(11
				(theSound1 number: 374 play:)
				(curRoom newRoom: 370)
				(graceActor dispose:)
				(self dispose:)
			)
		)
	)
)

(instance graceDoStuff of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if local9
					(self cue:)
					(= local9 0)
				else
					(chair init:)
					(graceProp
						init:
						view: 208
						loop: 0
						cel: 0
						cycleSpeed: 8
						setCycle: End self
					)
				)
			)
			(1
				(graceProp
					view: 208
					loop: 1
					cel: 0
					cycleSpeed: 8
					setCycle: End self
				)
			)
			(2 (= seconds 8))
			(3
				(graceProp loop: 2 cel: 0 setCycle: End self)
			)
			(4 (= seconds 8))
			(5 (self changeState: 1))
		)
	)
)

(instance talkToGrace of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (and (!= (ego x?) 235) (!= (ego y?) 127))
					(ego setMotion: PolyPath 235 127 self)
				else
					(self changeState: 2)
				)
			)
			(1 (Face ego graceProp self))
			(2
				(graceDoStuff dispose:)
				(graceProp view: 208 loop: 1 cel: 0)
				(= cycles 2)
			)
			(3
				(graceProp
					loop: 0
					cel: 4
					cycleSpeed: 8
					setCycle: Beg self
				)
			)
			(4
				(= local10 (Random 81 86))
				(switch currentDay
					(6
						(if local2
							(= local2 0)
							(messager say: 31 11 63 0 self)
						else
							(messager say: 31 11 86 0 self)
						)
					)
					(7
						(messager say: 31 11 21 0 self)
					)
					(else 
						(if local2
							(messager say: 31 11 14 1 self)
						else
							(messager say: 31 11 local10 1 self)
						)
					)
				)
			)
			(5
				(if local2
					(= local2 0)
					(messager say: 31 11 14 2 self)
				else
					(messager say: 31 11 local10 2 self)
				)
				(graceProp
					view: 2111
					loop: 0
					cel: 0
					setCycle: ROsc -1 0 4
				)
			)
			(6
				(graceProp setCycle: 0)
				(if (or (== local10 83) (== local10 86))
					(messager say: 31 11 local10 3 self)
				else
					(theGame handsOn:)
					(graceProp setScript: graceDoStuff)
					(self dispose:)
				)
			)
			(7
				(messager say: 31 11 local10 4 self)
				(graceProp
					view: 2111
					loop: 0
					cel: 0
					setCycle: ROsc -1 0 4
				)
			)
			(8
				(graceProp setCycle: 0)
				(theGame handsOn:)
				(graceProp setScript: graceDoStuff)
				(self dispose:)
			)
		)
	)
)

(instance interrogateGrace of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (and (!= (ego x?) 235) (!= (ego y?) 127))
					(ego setMotion: PolyPath 235 127 self)
				else
					(self changeState: 2)
				)
			)
			(1 (Face ego graceProp self))
			(2
				(graceDoStuff dispose:)
				(graceProp view: 208 loop: 1 cel: 0 setCycle: 0)
				(= cycles 2)
			)
			(3
				(messager say: 31 10 0 1 self)
			)
			(4
				(graceProp
					loop: 0
					cel: 4
					cycleSpeed: 8
					setCycle: Beg self
				)
			)
			(5
				(messager say: 31 10 0 2 self)
				(graceProp
					view: 2111
					loop: 0
					cel: 0
					setCycle: ROsc -1 0 4
				)
			)
			(6
				(= interrogateSubject 2)
				(theGame handsOn:)
				(curRoom newRoom: 50)
				(self dispose:)
			)
		)
	)
)

(instance showGraceItem of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if
				(not (if (== (ego x?) 187) (== (ego y?) 125)))
					(ego setMotion: PolyPath 187 125 self)
				else
					(self changeState: 2)
				)
				(graceDoStuff dispose:)
			)
			(1 (Face ego graceProp self))
			(2
				(messager say: 31 0 0 1 self)
			)
			(3
				(graceProp
					loop: 0
					cel: 4
					cycleSpeed: 8
					setCycle: Beg self
				)
			)
			(4
				(graceProp view: 2111 loop: 0 cel: 0 setCycle: End)
				(messager say: 31 0 0 2 self)
				(= local9 1)
			)
			(5
				(theGame handsOn:)
				(graceProp setScript: graceDoStuff)
				(self dispose:)
			)
		)
	)
)

(instance getTheSnakeBook of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (ego inRect: 211 144 235 165)
					(ego setMotion: PolyPath 209 129 self)
				else
					(self cue:)
				)
			)
			(1
				(ego setMotion: PolyPath 187 101 self)
			)
			(2
				(ego view: 2152 loop: 1 cel: 0 setCycle: End self)
			)
			(3
				(messager say: 51 12 0 1 self)
			)
			(4
				(ego loop: 2 cel: 0 setCycle: CT 3 1 self)
			)
			(5
				(ego loop: 2 cel: 4 setCycle: End self)
				(theSound1 number: 218 play:)
			)
			(6
				(messager say: 51 12 0 2 4 self)
			)
			(7
				(if (Btst 6)
					(messager say: 51 12 10 2 self)
				else
					(= cycles 1)
				)
			)
			(8
				(ego loop: 2 cel: 10 setCycle: CT 7 -1 self)
			)
			(9
				(theSound1 number: 218 play:)
				(ego loop: 2 cel: 6 getPoints: 120 1 setCycle: Beg self)
			)
			(10
				(theGame handsOn:)
				(GKEgo normalize: 0 901)
				(ego ignoreActors: 1 ignoreControl: -32768)
				(self dispose:)
			)
		)
	)
)

(instance getTheObject of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 218 165 self)
			)
			(1 (Face ego graceProp self))
			(2 (= cycles 4))
			(3
				(if (== theMagnifyingGlass tweezers)
					(messager say: 39 12 0 0 self)
				else
					(messager say: 33 12 0 0 self)
				)
			)
			(4
				(Face ego theMagnifyingGlass self)
			)
			(5
				(ego view: 2152 loop: 0 cel: 0 setCycle: CT 2 1 self)
			)
			(6
				(theMagnifyingGlass dispose:)
				(= cycles 1)
			)
			(7
				(ego view: 2152 loop: 0 cel: 3 setCycle: End self)
			)
			(8
				(if (== theMagnifyingGlass tweezers)
					(ego get: 3 getPoints: 116 1)
				else
					(ego get: 4 getPoints: 117 1)
				)
				(theGame handsOn:)
				(GKEgo normalize: 1 901)
				(ego ignoreActors: 1 ignoreControl: -32768)
				(self dispose:)
			)
		)
	)
)

(instance exitToGabsRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego ignoreActors: 1 setMotion: PolyPath 298 103 self)
			)
			(1
				(ego
					posn: 298 107
					view: 220
					loop: 0
					cel: 0
					setCycle: End self
				)
			)
			(2
				(curRoom newRoom: 220)
				(self dispose:)
			)
		)
	)
)

(instance startOfDay7 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset 10)
				(ego init: posn: 291 125 get: 23)
				(graceProp init: setScript: graceDoStuff)
				(graceTeller init: graceProp)
			)
			(1 (self dispose:))
		)
	)
)

(instance enterTheFlorist of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(florist
					init:
					view: 217
					posn: 92 95
					loop: 1
					cel: 0
					ignoreActors: 1
					ignoreControl: -32768
					setCycle: End self
				)
				(floristTeller init: florist)
				(if (ego inRect: 105 90 220 106)
					(ego setMotion: PolyPath 160 125)
				)
			)
			(1
				(florist
					loop: 0
					cel: 0
					setCycle: Walk
					xStep: 4
					yStep: 3
					setMotion: MoveTo 144 112 self
				)
			)
			(2
				(graceDoStuff dispose:)
				(graceProp
					loop: 0
					cel: 4
					cycleSpeed: 8
					setCycle: Beg self
				)
			)
			(3
				(if (== currentDay 1)
					(messager say: 1 0 32 1 7 self)
				else
					(messager say: 1 0 51 1 3 self)
				)
			)
			(4
				(florist
					loop: 0
					cel: 0
					setCycle: Walk
					setMotion: MoveTo 210 107 self
				)
			)
			(5
				(if (== currentDay 1)
					(messager say: 1 0 32 8 self)
				else
					(messager say: 1 0 51 4 self)
				)
			)
			(6
				(ego
					ignoreActors: 1
					ignoreControl: -32768
					setMotion: PolyPath 277 106 self
				)
			)
			(7
				(ego view: 2171 loop: 2 cel: 0 setCycle: End self)
			)
			(8 (= seconds 3))
			(9
				(ego loop: 0 cel: 0 setCycle: End self)
			)
			(10 (floristTeller doVerb:))
			(11)
			(12
				(ego loop: 3 cel: 0 setCycle: End self)
			)
			(13)
			(14
				(florist view: 2171 loop: 6 cel: 0 setCycle: End)
				(ego view: 2171 loop: 4 cel: 0 setCycle: End self)
			)
			(15
				(painting dispose:)
				(ego loop: 5 cel: 0 setCycle: CT 6 1 self)
			)
			(16
				(florist loop: 7 cel: 0 setCycle: CT 2 1)
				(ego loop: 5 cel: 7 get: 6 setCycle: CT 8 1 self)
			)
			(17
				(florist loop: 7 cel: 3 setCycle: End)
				(ego loop: 5 cel: 9 setCycle: End self)
			)
			(18
				(GKEgo normalize: 1 901)
				(if local3
					(florist
						view: 2172
						setLoop: 0
						cel: 0
						setCycle: Walk
						ignoreControl: -32768
						setMotion: MoveTo 144 112 self
					)
				else
					(florist
						view: 217
						setLoop: 2
						cel: 0
						setCycle: Walk
						ignoreControl: -32768
						setMotion: MoveTo 144 112 self
					)
				)
			)
			(19
				(florist setMotion: PolyPath 92 95 self)
			)
			(20
				(if local3
					(florist loop: 1 cel: 0 setCycle: End self)
				else
					(florist loop: 3 cel: 0 setCycle: End self)
				)
			)
			(21
				(ego ignoreControl: -32768 ignoreActors: 1)
				(theGame handsOn:)
				(florist dispose:)
				(graceProp setScript: graceDoStuff)
				(entranceTimer client: 0)
				(entranceTimer dispose: delete:)
				(self dispose:)
			)
		)
	)
)

(instance openTheRegister of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (ego inRect: 62 117 114 141)
					(ego setMotion: PolyPath 148 127 self)
				else
					(self cue:)
				)
			)
			(1
				(ego setMotion: PolyPath 241 123 self)
			)
			(2 (Face ego cashRegister self))
			(3
				(Load rsAUDIO 211)
				(if (not (Btst 53))
					(Bset 53)
					(messager say: 13 6 8 0 self)
				else
					(messager say: 13 6 9 0 self)
				)
			)
			(4
				(ego view: 216 loop: 1 cel: 0 setCycle: CT 6 1 self)
			)
			(5
				(theSound1 number: 211 play:)
				(ego view: 216 loop: 1 cel: 7 setCycle: End self)
			)
			(6
				(UnLoad 141 211)
				(theGame handsOn:)
				(theIconBar disable: 0)
				(= cycles 1)
			)
			(7
				(drawer init: self bookstore)
			)
			(8
				(theGame handsOff:)
				(theIconBar enable: 0)
				(Load rsAUDIO 212)
				(cond 
					(local6
						(messager say: 14 12 0 0 self)
						(ego get: 5 getPoints: 121 1)
						(= local6 0)
					)
					(local7
						(if (Btst 6)
							(messager say: 15 12 10 0 self)
						else
							(messager say: 15 12 11 0 self)
						)
						(= local7 0)
					)
					(registerOpen (messager say: 16 6 0 0 self) (= registerOpen 0))
					(else (self cue:))
				)
			)
			(9
				(ego loop: 2 cel: 0 setCycle: CT 1 1 self)
			)
			(10
				(theSound1 number: 212 play:)
				(ego loop: 2 cel: 2 setCycle: End self)
			)
			(11
				(theGame handsOn:)
				(GKEgo normalize: 1 901)
				(ego ignoreActors: 1 ignoreControl: -32768)
				(UnLoad 141 212)
				(self dispose:)
			)
		)
	)
)

(instance tatooStuff of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 187 125 self)
			)
			(1 (Face ego graceProp self))
			(2
				(if (Btst 54)
					(messager say: 31 30 67 0 self)
				else
					(Bset 54)
					(messager say: 31 30 66 0 self)
				)
			)
			(3
				(graceTeller doVerb:)
				(self cue:)
			)
			(4
				(graceDoStuff dispose:)
				(graceProp view: 208 loop: 4 cel: 7 setCycle: Beg self)
			)
			(5
				(graceProp dispose:)
				(graceActor init: view: 223 posn: 246 116)
				(ego ignoreActors: 1 setMotion: PolyPath 298 103 self)
			)
			(6
				(graceActor
					ignoreControl: -32768
					setCycle: Walk
					setMotion: MoveTo 269 104 self
				)
				(ego
					posn: 298 107
					view: 220
					loop: 0
					cel: 0
					setCycle: End self
				)
			)
			(7)
			(8
				(graceActor setMotion: MoveTo 302 106 self)
			)
			(9
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance getNumFromGrace of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(graceDoStuff dispose:)
				(theGame handsOff:)
				(ego hide:)
				(= cycles 1)
			)
			(1
				(graceProp
					view: 213
					posn: 222 118
					loop: 1
					setCycle: End self
				)
			)
			(2
				(graceProp
					posn: 251 115
					cel: 0
					loop: 2
					setCycle: End self
				)
				(ego show: get: 8)
			)
			(3
				(messager say: 1 0 87 0 self)
			)
			(4
				(graceProp setScript: graceDoStuff)
				(theGame handsOn:)
				(GKEgo normalize: 0 901)
				(ego ignoreActors: 1 ignoreControl: -32768)
				(self dispose:)
			)
		)
	)
)

(instance getTheNote of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 241 122 self)
			)
			(1 (Face ego maliasNote self))
			(2
				(maliasNote dispose:)
				(ego view: 2161 loop: 0 cel: 0 setCycle: End self)
			)
			(3
				(messager say: 34 12 0 0 self)
			)
			(4 (ego setCycle: Beg self))
			(5
				(maliasNote init:)
				(messager say: 1 0 77 1 self)
			)
			(6
				(ego loop: 1 cel: 0 setCycle: End self)
			)
			(7
				(ego loop: 2 cel: 0 setCycle: End self)
			)
			(8
				(ego
					posn: 240 129
					setLoop: 3
					cel: 0
					setCycle: Walk
					setMotion: MoveTo 225 142 self
				)
			)
			(9
				(messager say: 1 0 77 2 3 self)
			)
			(10
				(mosley init: loop: 0 cel: 0 setCycle: End self)
			)
			(11
				(mosley loop: 1 cel: 0 setCycle: End self)
			)
			(12
				(messager say: 1 0 77 4 self)
			)
			(13
				(ego loop: 4 cel: 0 setCycle: End self)
			)
			(14
				(ego loop: 5 cel: 0 setCycle: End self)
			)
			(15
				(UnLoad 128 2161)
				(theGame handsOn:)
				(GKEgo normalize:)
				(self dispose:)
			)
		)
	)
)

(instance graceGetsTheScale of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					posn: 107 100
					view: 219
					setLoop: 2
					cel: 0
					setCycle: End self
				)
				(graceProp init: view: 2081 loop: 0 cel: 0)
			)
			(1
				(ego
					posn: 88 97
					loop: 3
					setPri: 8
					cel: 0
					setCycle: End self
				)
			)
			(2
				(GKEgo normalize: 0 901)
				(gabsCoat init:)
				(Face ego graceProp)
				(= cycles 5)
			)
			(3
				(messager say: 1 0 80 1 3 self)
			)
			(4
				(ego
					ignoreActors: 1
					ignoreControl: -32768
					setMotion: PolyPath 274 118 self
				)
			)
			(5
				(Face ego graceProp)
				(graceProp setCycle: End self)
			)
			(6
				(messager say: 1 0 80 4 6 self)
			)
			(7
				(ego hide:)
				(graceProp loop: 1 cel: 0 setCycle: End self)
			)
			(8
				(ego show:)
				(graceProp loop: 0 cel: 0 setCycle: End self)
			)
			(9
				(messager say: 1 0 80 7 9 self)
			)
			(10
				(graceProp setScript: graceDoStuff)
				(self cue:)
			)
			(11
				(= local4 1)
				(GKEgo normalize: 1 901)
				(ego ignoreActors: 1 ignoreControl: -32768)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance takeTheCertificate of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(giftCertificate hide:)
				(= local6 1)
				(= cycles 10)
			)
			(1
				(drawer dispose:)
				(self dispose:)
			)
		)
	)
)

(instance egoClimbTheLadder of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (ego inRect: 211 144 235 165)
					(ego setMotion: PolyPath 209 129 self)
				else
					(self cue:)
				)
			)
			(1
				(if
				(not (if (== (ego x?) 175) (== (ego y?) 98)))
					(ego setMotion: PolyPath 175 98 self)
				else
					(self cue:)
				)
			)
			(2
				(ego
					posn: 170 98
					view: 2102
					setLoop: 0
					cel: 0
					setCycle: End self
				)
				(graceDoStuff dispose:)
			)
			(3
				(graceProp
					loop: 0
					cel: 4
					cycleSpeed: 8
					setCycle: Beg self
				)
			)
			(4
				(messager say: 32 8 10 1 self)
				(graceProp
					view: 2111
					loop: 0
					cel: 0
					setCycle: ROsc 20 0 4
				)
			)
			(5
				(graceProp setCycle: 0)
				(messager say: 32 8 10 2 self)
			)
			(6
				(messager say: 32 8 10 3 self)
				(graceProp
					view: 2111
					loop: 0
					cel: 0
					setCycle: ROsc 20 0 4
				)
			)
			(7
				(graceProp setCycle: 0)
				(ego setCycle: Beg self)
			)
			(8
				(ego
					posn: 175 98
					normalize: 3 901
					ignoreActors: 1
					ignoreControl: -32768
				)
				(= ticks 1)
			)
			(9 (Face ego graceProp self))
			(10
				(messager say: 32 8 10 4 self)
			)
			(11
				(theGame handsOn:)
				(graceProp setScript: graceDoStuff)
				(self dispose:)
			)
		)
	)
)

(instance drawer of Inset
	(properties
		view 216
		x 141
		y 27
		disposeNotOnMe 1
	)
	
	(method (init)
		(self priority: 12)
		(super init: &rest)
		(if (not (ego has: 5)) (giftCertificate init:))
		(money init:)
		(allElse init:)
	)
	
	(method (dispose)
		(giftCertificate dispose:)
		(money dispose:)
		(allElse dispose:)
		(super dispose:)
		(if (theGame keepBar?) (theIconBar draw:))
	)
)

(instance graceTeller of GKTeller
	(properties
		curNoun 49
		sayNoun 43
		verb 53
	)
	
	(method (doVerb theVerb)
		(return
			(if (== (ego script?) tatooStuff)
				(SetCursor -2)
				(repeat
					(if (self respond:) (break))
				)
				(SetCursor 0 0 155 319)
				(return 1)
			else
				(client doVerb: theVerb)
				(return 1)
			)
		)
	)
	
	(method (cue)
		(cond 
			(
				(not
					(if
						(or
							(== iconValue 74)
							(== iconValue 76)
							(== iconValue 75)
							(== iconValue 69)
							(== iconValue 70)
						)
					else
						(== iconValue 73)
					)
				)
				(self doVerb: 11)
			)
			((== iconValue 70) (tatooStuff cue:))
			(else
				(self newNoun: 49)
				(tatooStuff dispose:)
				(GKEgo normalize: 0 901)
				(ego ignoreActors: 1 ignoreControl: -32768)
				(theGame handsOn:)
			)
		)
	)
)

(instance floristTeller of GKTeller
	(properties
		curNoun 42
		sayNoun 43
		verb 2
	)
	
	(method (doVerb)
		(= theGameSetCursor (theGame setCursor:))
		(SetCursor -2)
		(theGame setCursor: 999 1)
		(repeat
			(if (self respond:) (break))
		)
		(SetCursor 0 0 153 319)
		(theGame setCursor: theGameSetCursor 1)
		(return 1)
	)
	
	(method (sayMessage)
		(if
			(or
				(== iconValue 47)
				(== iconValue 39)
				(== iconValue 46)
				(== iconValue 49)
			)
			(= local3 0)
			(enterTheFlorist changeState: 18)
		)
		(super sayMessage:)
	)
	
	(method (showCases)
		(super showCases: 41 (Btst 35) 38 (not (Btst 35)))
	)
	
	(method (cue)
		(if
			(not
				(if
					(or
						(== iconValue 47)
						(== iconValue 39)
						(== iconValue 46)
						(== iconValue 49)
					)
				else
					(= local3 1)
				)
			)
			(enterTheFlorist cue:)
		)
		(if
			(not
				(if
					(or
						(== iconValue 47)
						(== iconValue 39)
						(== iconValue 46)
						(== iconValue 49)
					)
				else
					(== iconValue 48)
				)
			)
			(self doVerb: 11)
		)
	)
)

(instance mosley of Actor
	(properties
		x 304
		y 106
		view 2162
	)
)

(instance graceActor of Actor
	(properties
		x 251
		y 115
		view 209
	)
)

(instance florist of Actor
	(properties
		x 210
		y 107
		noun 42
		view 2173
	)
)

(instance graceProp of Prop
	(properties
		x 251
		y 115
		noun 31
		sightAngle 50
		view 211
		signal $1000
	)
	
	(method (doVerb theVerb)
		(if
			(or
				(== theVerb 7)
				(== theVerb 8)
				(== theVerb 9)
				(== theVerb 12)
				(== theVerb 6)
			)
			(super doVerb: theVerb)
		else
			(switch theVerb
				(11
					(ego setScript: talkToGrace)
				)
				(10
					(ego setScript: interrogateGrace)
				)
				(30
					(if (not (== currentDay 6))
						(messager say: noun theVerb 68 0)
					else
						(ego setScript: tatooStuff)
					)
				)
				(else 
					(ego setScript: showGraceItem)
				)
			)
		)
	)
)

(instance shopDoor of Prop
	(properties
		x 93
		y 88
		noun 38
		sightAngle 20
		view 210
		loop 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(6 (ego setScript: putCoatOn))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance gabsCoat of View
	(properties
		x 104
		y 63
		noun 20
		sightAngle 20
		view 210
		cel 1
	)
)

(instance gracesCoat of View
	(properties
		x 111
		y 60
		noun 21
		sightAngle 20
		view 210
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(7
				(if (Btst 6)
					(messager say: noun theVerb 10 0)
				else
					(messager say: noun theVerb 11 0)
				)
			)
			(8
				(if (not (Btst 6))
					(messager say: noun theVerb 11 0)
				else
					(messager say: noun theVerb 10 0)
				)
			)
			(12
				(if (not (Btst 6))
					(messager say: noun theVerb 11 0)
				else
					(messager say: noun theVerb 10 0)
				)
			)
			(else 
				(cond 
					((OneOf theVerb 8 6 12 10 11 9 7 13) (super doVerb: theVerb))
					((not (Btst 6)) (messager say: noun 0 11 0))
					(else (messager say: noun 0 10 0))
				)
			)
		)
	)
)

(instance newsPaper of View
	(properties
		x 136
		y 154
		z 31
		noun 35
		sightAngle 20
		view 210
		cel 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(12
				(ego setScript: getTheNewsPaper)
			)
			(7
				(if (== currentDay 11)
					(messager say: noun theVerb 79 0)
				else
					(messager say: noun theVerb 0 0)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance magnifyingGlass of View
	(properties
		x 197
		y 135
		noun 33
		sightAngle 20
		view 210
		cel 4
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 194 130 209 135 202 139 189 135
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(7
				(messager say: noun theVerb 0 0)
			)
			(12
				(= theMagnifyingGlass magnifyingGlass)
				(ego setScript: getTheObject)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance tweezers of View
	(properties
		x 202
		y 132
		noun 39
		sightAngle 20
		view 210
		cel 5
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 198 127 212 129 208 136 193 132
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(12
				(= theMagnifyingGlass tweezers)
				(ego setScript: getTheObject)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance coffeePot of View
	(properties
		x 94
		y 117
		noun 23
		view 210
		cel 6
	)
	
	(method (init)
		(self setPri: 13)
		(super init: &rest)
	)
)

(instance chair of View
	(properties
		x 258
		y 101
		noun 17
		view 210
		cel 9
		signal $1000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(7
				(if (== currentDay 11)
					(messager say: noun theVerb 22 0)
				else
					(messager say: noun theVerb 0 0)
				)
			)
			(12
				(if (== currentDay 11)
					(messager say: noun theVerb 22 0)
				else
					(messager say: noun theVerb 0 0)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance gabsMug of View
	(properties
		x 118
		y 115
		view 210
		cel 7
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 113 112 125 113 125 119 114 119
					yourself:
				)
		)
		(super init:)
	)
)

(instance painting of View
	(properties
		x 269
		y 48
		noun 36
		sightAngle 20
		approachX 271
		approachY 120
		view 210
		cel 8
		signal $1000
	)
	
	(method (init)
		(self approachVerbs: 9 6 12 11)
		(super init: &rest)
	)
)

(instance ashTray of View
	(properties
		x 227
		y 81
		noun 47
		sightAngle 20
		view 210
		cel 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(7
				(if local4
					(messager say: noun theVerb 62 0)
				else
					(messager say: noun theVerb 61 0)
				)
			)
			(4
				(if local4
					(messager say: noun theVerb 62 0)
				else
					(messager say: noun theVerb 61 0)
				)
			)
			(12
				(if local4
					(messager say: noun theVerb 62 0)
				else
					(messager say: noun theVerb 61 0)
				)
			)
			(3
				(if local4
					(messager say: noun theVerb 62 0)
				else
					(messager say: noun theVerb 61 0)
				)
			)
			(else  (super doVerb: 0 &rest))
		)
	)
)

(instance maliasNote of View
	(properties
		x 225
		y 94
		view 210
		cel 12
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(12 (ego setScript: getTheNote))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance artSupplies of View
	(properties
		x 279
		y 103
		noun 3
		sightAngle 20
		view 210
		cel 11
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 287 87 285 101 272 100 274 88
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 7)
			(super doVerb: theVerb)
		else
			(super doVerb: 0)
		)
	)
)

(instance giftCertificate of View
	(properties
		x 173
		y 76
		noun 14
		view 216
		cel 1
	)
	
	(method (init)
		(self setPri: 13)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(12
				(drawer setScript: takeTheCertificate)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance coatRack of Feature
	(properties
		x 108
		y 58
		noun 22
		nsTop 42
		nsLeft 103
		nsBottom 85
		nsRight 114
		sightAngle 20
		approachX 112
		approachY 98
	)
	
	(method (init)
		(self approachVerbs: 9 8 12)
		(super init:)
	)
)

(instance coffeeStuff of Feature
	(properties
		x 118
		y 114
		noun 23
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init:
						89
						102
						101
						97
						112
						100
						112
						106
						116
						106
						116
						112
						123
						112
						123
						116
						109
						125
						87
						121
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(12
				(if local1
					(= local1 0)
					(curRoom setScript: getSomeCoffee)
				else
					(messager say: noun 12 13 1)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance balcony of Feature
	(properties
		x 31
		y 40
		noun 4
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:) type: 0 init: 1 22 136 22 0 78 yourself:)
		)
		(super init:)
	)
)

(instance snakeBooks of Feature
	(properties
		x 200
		y 30
		noun 51
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 170 22 213 22 212 36 171 34
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(12
				(ego setScript: getTheSnakeBook)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance booksBehindLadder of Feature
	(properties
		x 200
		y 45
		noun 5
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 169 38 213 40 213 72 201 77 201 85 190 90 171 85
					yourself:
				)
		)
		(super init:)
	)
)

(instance booksLeftOfLadder of Feature
	(properties
		x 137
		y 50
		noun 6
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 115 35 161 39 157 84 118 78
					yourself:
				)
		)
		(super init:)
	)
)

(instance booksBottomRight of Feature
	(properties
		x 308
		y 126
		noun 7
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init:
						319
						145
						267
						145
						267
						140
						277
						132
						285
						132
						286
						102
						299
						96
						305
						97
						311
						92
						319
						92
					yourself:
				)
		)
		(super init:)
	)
)

(instance booksBottomLeft of Feature
	(properties
		x 42
		y 138
		noun 8
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 1 115 24 119 26 124 72 132 78 128 102 134 104 141 118 145 0 145
					yourself:
				)
		)
		(super init:)
	)
)

(instance coffeeBooks of Feature
	(properties
		x 172
		y 127
		noun 9
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init:
						143
						122
						160
						123
						162
						117
						172
						117
						178
						127
						191
						128
						192
						131
						189
						136
						174
						135
						173
						133
						165
						131
						156
						134
						143
						127
					yourself:
				)
		)
		(super init:)
	)
)

(instance germanBooks of Feature
	(properties
		x 147
		y 27
		noun 12
		nsTop 22
		nsLeft 128
		nsBottom 32
		nsRight 162
		sightAngle 20
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(12
				(ego setScript: getTheGermanBook)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bookRack of Feature
	(properties
		x 257
		y 134
		noun 11
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 241 145 243 129 251 125 256 119 261 117 264 126 270 134 266 144
					yourself:
				)
		)
		(super init:)
	)
)

(instance windowBooks of Feature
	(properties
		x 43
		y 101
		noun 10
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 0 103 65 74 76 81 78 100 25 125 25 120 3 114 0 116
					yourself:
				)
		)
		(super init:)
	)
)

(instance chandelier of Feature
	(properties
		x 167
		y 52
		z 1000
		noun 18
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init:
						162
						22
						162
						43
						168
						35
						175
						36
						177
						43
						173
						47
						173
						50
						172
						56
						167
						57
						171
						61
						180
						60
						177
						52
						179
						47
						184
						46
						189
						53
						185
						56
						183
						60
						178
						63
						168
						63
						162
						62
						152
						66
						142
						59
						136
						52
						131
						50
						134
						42
						142
						41
						142
						49
						144
						55
						147
						50
						154
						50
						158
						44
						159
						22
					yourself:
				)
		)
		(super init:)
	)
)

(instance gargoyle of Feature
	(properties
		x 208
		y 116
		noun 30
		sightAngle 20
		approachX 196
		approachY 132
	)
	
	(method (init)
		(self
			approachVerbs: 9 8 12 11
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 216 100 216 122 209 126 194 121 194 106 196 98
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(11
				(if (Btst 6)
					(messager say: noun 11 10 0)
				else
					(messager say: noun 11 11 0)
				)
			)
			(10
				(if (Btst 6)
					(messager say: noun 10 10 0)
				else
					(messager say: noun 10 11 0)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance rightWindow of Feature
	(properties
		x 19
		y 81
		noun 41
		sightAngle 20
		approachX 60
		approachY 116
	)
	
	(method (init)
		(self
			approachVerbs: 7 6
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 45 60 43 83 25 90 24 68
					yourself:
				)
		)
		(super init:)
	)
)

(instance leftWindow of Feature
	(properties
		x 19
		y 80
		noun 41
		sightAngle 20
		approachX 60
		approachY 116
	)
	
	(method (init)
		(self
			approachVerbs: 7 6
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 8 76 8 97 0 103 0 79
					yourself:
				)
		)
		(super init:)
	)
)

(instance drawers of Feature
	(properties
		x 231
		y 105
		noun 28
		nsTop 96
		nsLeft 229
		nsBottom 112
		nsRight 237
		sightAngle 20
	)
)

(instance desk of Feature
	(properties
		x 200
		y 99
		noun 26
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 191 91 223 96 267 74 267 100 224 124 192 119
					yourself:
				)
		)
		(super init:)
	)
)

(instance curtainDoor of Feature
	(properties
		x 300
		y 55
		noun 25
		nsTop 42
		nsLeft 298
		nsBottom 93
		nsRight 319
		sightAngle 20
		approachX 285
		approachY 136
	)
	
	(method (init)
		(self approachVerbs: 11)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(6
				(if isDemo (messager say: 1 0 88 0))
			)
			(11
				(if (Btst 6)
					(messager say: noun theVerb 10 0)
				else
					(messager say: noun theVerb 11 0)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance ladder of Feature
	(properties
		x 215
		y 90
		noun 32
		sightAngle 30
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init:
						201
						22
						192
						97
						189
						97
						189
						90
						174
						88
						174
						95
						169
						94
						178
						21
						182
						21
						175
						84
						189
						86
						190
						79
						180
						77
						181
						75
						189
						76
						192
						64
						181
						65
						183
						62
						191
						62
						194
						53
						188
						53
						188
						51
						193
						50
						194
						41
						183
						40
						183
						37
						194
						38
						194
						29
						184
						28
						185
						26
						196
						25
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(8
				(if (Btst 6)
					(ego setScript: egoClimbTheLadder)
				else
					(messager say: noun theVerb 11 0)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance cashRegister of Feature
	(properties
		y 83
		noun 13
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 228 90 219 94 202 91 202 77 211 73 215 74 216 76 224 84
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(6
				(ego setScript: openTheRegister)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance money of Feature
	(properties
		y 190
		noun 15
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 236 27 282 52 281 63 199 107 179 94 216 75 183 52
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(12
				(= local7 1)
				(drawer dispose:)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance allElse of Feature
	(properties
		y 76
		noun 16
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 281 27 281 110 141 110 141 27
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(6
				(= registerOpen 1)
				(drawer dispose:)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance coffeeTable of Feature
	(properties
		x 134
		y 199
		noun 24
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init:
						212
						144
						119
						144
						108
						140
						107
						138
						103
						132
						82
						126
						80
						123
						84
						121
						129
						130
						141
						128
						144
						133
						200
						141
						213
						131
					yourself:
				)
		)
		(super init:)
	)
)

(instance doorMat of Feature
	(properties
		x 91
		y 97
		noun 52
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 78 99 77 94 91 87 116 93 108 99 100 97 92 102
					yourself:
				)
		)
		(super init:)
	)
)

(instance deskLamp of Feature
	(properties
		x 222
		y 68
		noun 53
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 226 79 219 78 221 72 217 67 226 62 234 65 224 70 223 76
					yourself:
				)
		)
		(super init:)
	)
)

(instance deskTop of Feature
	(properties
		x 225
		y 48
		noun 27
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 193 89 226 73 238 75 251 72 269 74 221 97
					yourself:
				)
		)
		(super init:)
	)
)

(instance phone of Feature
	(properties
		x 254
		y 70
		noun 37
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 259 69 253 75 247 69
					yourself:
				)
		)
		(super init:)
	)
)
