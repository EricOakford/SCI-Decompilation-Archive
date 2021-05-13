;;; Sierra Script 1.0 - (do not remove this comment)
(script# 630)
(include sci.sh)
(use Main)
(use LbDoor)
(use LBRoom)
(use n027)
(use MuseumRgn)
(use Inset)
(use PolyPath)
(use Feature)
(use ForCount)
(use LoadMany)
(use Timer)
(use Sound)
(use Motion)
(use Invent)
(use Actor)
(use System)

(public
	rm630 0
	labDoor 2
)

(local
	local0
	local1
	local2
)
(instance rm630 of LBRoom
	(properties
		noun 21
		picture 630
		east 610
		vanishingY 105
	)
	
	(method (init)
		(ego init: setScale: 0 normalize: 827 xStep: 4)
		(self setRegions: 90)
		(if (not (Btst 4))
			(cond 
				((not (TriggerEvent -20222)) 0)
				((not (TriggerEvent 4880)) (Bclr 18))
				(else 0)
			)
		)
		(switch prevRoomNum
			(east
				(ego edgeHit: 0 setHeading: 270)
			)
			(else 
				(ego wearingGown: 1 posn: 202 158)
				(= currentAct 4)
				(theGame handsOn:)
			)
		)
		(super init:)
		(LoadMany 132 631 632 633 634)
		(labDoor init:)
		(trunk approachVerbs: 1 4 8 init:)
		(fridgeDoor approachVerbs: 4 init:)
		(bag init:)
		(if (== ((Inv at: 14) owner?) 630)
			(snakeOil init: approachVerbs: 4 1 8)
		)
		(if
			(or
				(and (== currentAct 3) (TriggerEvent 4104))
				(>= currentAct 4)
			)
			(LoadMany 128 630 631 633 632 634 635)
			(LoadMany 132 631 632 633 634 85 635 636)
			(boxFrontLeft init:)
			(assortedBoxes init:)
			(meatLocker init:)
			(leftShelves init:)
			(cabinet init:)
			(crate2 init:)
			(crate3 init:)
			(poster init:)
			(assortedTools init:)
			(canister init:)
			(light init:)
			(rightShelf init:)
			(desk init:)
			(crate1 x: (if (Btst 119) 105 else 138) init:)
			(if (Btst 12)
				(trunk x: 299 setCel: 1 stopUpd:)
				(trunkLid init: approachVerbs: 1 4 8)
				(if (Btst 13)
					(trunkLid setCel: (trunkLid lastCel:) stopUpd:)
				)
			else
				(LoadMany 132 635 636)
			)
			(if (Btst 14)
				(fridgeDoor setCel: (fridgeDoor lastCel:) stopUpd:)
			)
			(if (and (not (Btst 12)) (not (Btst 60)))
				(ferret init:)
				(Load rsSOUND 637)
				(ferretTimer setReal: ferret (Random 5 30))
			)
		else
			(theMusic2 number: 520 loop: -1 flags: 1 play:)
			(crate1 init:)
			(olympia init:)
			(= local2 1)
		)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			((== script sGoTunnel))
			(
			(and (IsObjectOnControl ego 4) (== (crate1 x?) 105)) (self setScript: sGoTunnel))
		)
	)
	
	(method (dispose)
		(ferretTimer dispose: delete:)
		(theIconBar disable: 7)
		(theMusic2 fade:)
		(super dispose:)
	)
	
	(method (cue)
		(sPlayMusic dispose:)
		(theMusic2 fade:)
	)
)

(instance sGoTunnel of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setHeading: 0 self)
			)
			(1
				(ego setMotion: MoveTo 141 138 self)
			)
			(2
				(Bclr 119)
				(curRoom newRoom: 666)
				(self dispose:)
			)
		)
	)
)

(instance sKickedOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setHeading: 180 self)
			)
			(1 (= ticks 60))
			(2
				(messager say: 23 0 6)
				(= cycles 1)
			)
			(3
				(Bset 50)
				(labDoor doVerb: 4)
				(self dispose:)
			)
		)
	)
)

(instance sOpenFridge of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 633
					setLoop: 0
					setCel: 0
					cycleSpeed: 12
					setCycle: CT 1 1 self
				)
			)
			(1
				(ego setCycle: End)
				(fridgeDoor setCycle: End self)
				(sFX number: 631 flags: 1 play:)
			)
			(2
				(fridgeDoor stopUpd:)
				(ego setCycle: Beg self)
			)
			(3
				(ego loop: 1 normalize: 827 xStep: 4)
				(Bset 14)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sCloseFridge of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 633
					setLoop: 0
					setCel: 0
					cycleSpeed: 12
					setCycle: End self
				)
			)
			(1
				(ego setCycle: Beg)
				(fridgeDoor setCycle: Beg self)
			)
			(2
				(sFX number: 632 flags: 1 play:)
				(fridgeDoor stopUpd:)
				(Bclr 14)
				(ego loop: 1 normalize: 827 xStep: 4)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sFerretSniff of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ferret
					setLoop: 5
					setCel: 0
					posn: 116 151
					setCycle: Walk
					setMotion: MoveTo 216 149 self
				)
			)
			(1
				(ferret
					setLoop: 2
					setCel: 0
					setPri: 11
					cycleSpeed: 6
					setCycle: End self
				)
			)
			(2 (= ticks 30))
			(3 (ferret setCycle: Beg self))
			(4
				(ferret
					setLoop: 5
					setCycle: Walk
					cycleSpeed: 4
					setMotion: MoveTo 272 143 self
				)
			)
			(5
				(ferret
					setLoop: 0
					setCel: 0
					cycleSpeed: 6
					setCycle: ForwardCounter 2 self
				)
			)
			(6
				(sFX number: 637 flags: 1 play:)
				(= ticks 30)
			)
			(7
				(ferret
					setLoop: 5
					setCycle: Walk
					setMotion: MoveTo 280 142 self
				)
			)
			(8
				(ferret setLoop: 3 setCel: 0 setCycle: End self)
			)
			(9 (ferret setCycle: Beg self))
			(10
				(ferret setLoop: 2 setCel: 0 setCycle: End self)
			)
			(11
				(ferret
					posn: 268 142
					setLoop: 1
					setCel: (ferret lastCel:)
					setCycle: Beg self
				)
			)
			(12
				(ferret
					setLoop: 4
					cycleSpeed: 4
					setCycle: Walk
					setMotion: MoveTo 212 153 self
				)
			)
			(13
				(ferret setPri: -1 setMotion: MoveTo 158 164 self)
			)
			(14
				(Bset 60)
				(ferret stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance sUnlockTrunk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(fridgeDoor approachVerbs: 0)
				(snakeOil approachVerbs: 0)
				(labDoor approachVerbs: 0)
				(ego setMotion: PolyPath 258 144 self)
			)
			(1
				(ego
					view: 633
					setLoop: 1
					setCel: 0
					setPri: 12
					cycleSpeed: 12
					setCycle: CT 3 1 self
				)
			)
			(2
				(ego setCycle: CT 4 1 self)
				(trunk x: 314)
			)
			(3
				(ego setCycle: CT 5 1 self)
				(trunk x: 305)
			)
			(4
				(ego setCycle: CT 6 1 self)
				(trunk x: 299)
			)
			(5
				(trunk setCel: 1)
				(trunkLid
					init:
					approachVerbs: 1 4 8
					cycleSpeed: 12
					setCycle: End
				)
				(sFX number: 633 flags: 1 play:)
				(ego setCycle: End self)
			)
			(6
				(trunk stopUpd:)
				(trunkLid stopUpd:)
				(Bset 13)
				(theGame handsOn:)
				(theIconBar disable: 0)
				(= seconds (if (HaveMouse) 6 else 12))
			)
			(7
				(theGame handsOff:)
				(sFX number: 635 flags: 1 play:)
				(ego view: 634 setLoop: 0 setCel: 0 setCycle: End self)
			)
			(8
				(ego setLoop: 1 setCel: 0 setCycle: End self)
			)
			(9 (sFX stop:) (= ticks 120))
			(10
				(= deathReason 1)
				(curRoom newRoom: 99)
				(self dispose:)
			)
		)
	)
)

(instance sInsertMeat of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setLoop: 2 setCel: 0 setCycle: End self)
			)
			(1
				(ego
					setLoop: 1
					setCel: (ego lastCel:)
					setCycle: Beg self
				)
			)
			(2
				(ego normalize: 827 loop: 0 put: 9 xStep: 4)
				((ScriptID 21 1) doit: 778)
				(sFX2 number: 636 flags: 1 play:)
				(= ticks 120)
			)
			(3
				(bugsWithMeat init: setCycle: End self)
			)
			(4
				(bugsWithMeat
					view: 635
					setLoop: 0
					setCel: 0
					posn: 260 146
					setCycle: Fwd
					cycleSpeed: 4
					moveSpeed: 4
					setMotion: MoveTo 157 157 self
				)
			)
			(5
				(sFX2 fade:)
				(bugsWithMeat dispose:)
				(Bset 12)
				(MuseumRgn loadPolyList:)
				(fridgeDoor approachVerbs: 4)
				(snakeOil approachVerbs: 4 1 8)
				(labDoor approachVerbs: 4 18)
				(ferretTimer dispose: delete:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sOpenTrunk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 633
					setLoop: 1
					setCel: 0
					setPri: 12
					cycleSpeed: 12
					setCycle: CT 6 1 self
				)
			)
			(1
				(ego setCycle: End self)
				(trunkLid setCycle: End)
				(sFX number: 633 flags: 1 play:)
			)
			(2
				(ego setCel: 5 setCycle: Beg self)
			)
			(3
				(Bset 13)
				(trunkLid stopUpd:)
				(ego normalize: 827 loop: 0 posn: 258 144 xStep: 4)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sCloseTrunk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 633
					setLoop: 1
					setCel: 0
					setPri: 12
					cycleSpeed: 12
					setCycle: CT 5 1 self
				)
			)
			(1
				(ego setCel: (ego lastCel:) setCycle: CT 9 -1 self)
			)
			(2
				(ego setCycle: CT 6 -1 self)
				(trunkLid setCycle: Beg)
				(sFX number: 634 flags: 1 play:)
			)
			(3 (ego setCycle: Beg self))
			(4
				(Bclr 13)
				(trunkLid stopUpd:)
				(ego normalize: 827 loop: 0 posn: 258 144 xStep: 4)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sPlayMusic of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (Btst 87)
					(= cycles 1)
				else
					(theMusic2 number: 4 loop: 1 flags: 1 play: self)
					(sFX number: 85 loop: 1 flags: 1 play:)
					(Bset 87)
				)
			)
			(1
				(theMusic2 number: 6 loop: -1 flags: 1 play:)
			)
		)
	)
)

(instance labDoor of LbDoor
	(properties
		x 277
		y 73
		noun 20
		approachX 244
		approachY 143
		view 630
		loop 4
		priority 9
		signal $0010
		entranceTo 610
		moveToX 287
		moveToY 139
		enterType 0
		exitType 0
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if
					(and
						(curRoom script?)
						(!= (curRoom script?) sKickedOut)
					)
					0
				else
					(super doVerb: theVerb)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (cue)
		(super cue: &rest)
		(if (== doorState 0)
			(if local2
				(curRoom setScript: sKickedOut)
			else
				(theIconBar enable: 7)
			)
			(= priority 8)
		else
			(= priority 9)
		)
	)
	
	(method (createPoly)
		(super createPoly: 265 132 273 137 269 141 261 135)
	)
)

(instance ferretTimer of Timer
	(properties)
)

(instance trunk of Prop
	(properties
		x 319
		y 144
		noun 1
		approachX 258
		approachY 144
		view 631
		loop 1
		priority 10
		signal $4011
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(1
					(if (and (Btst 12) (trunkLid cel?))
						(theGame points: 1 147)
						(curRoom setInset: inBones)
					else
						(messager say: 1 1)
					)
				)
				(8
					(if (and (Btst 12) (trunkLid cel?))
						(theGame points: 1 147)
						(curRoom setInset: inBones)
					else
						(messager say: 1 8)
					)
				)
				(4
					(cond 
						((and (Btst 12) (not (trunkLid cel?)))
							(if (MuseumRgn nobodyAround:)
								(curRoom setScript: sOpenTrunk)
							else
								(return 1)
							)
						)
						((and (Btst 12) (trunkLid cel?)) (curRoom setScript: sCloseTrunk))
						((curRoom script?) 0)
						(else (messager say: 1 4 5))
					)
				)
				(18
					(if (not (curRoom script?))
						(cond 
							((Btst 12) (messager say: 1 18 7))
							((MuseumRgn nobodyAround:) (curRoom setScript: sUnlockTrunk))
							(else (return 1))
						)
					)
				)
				(19
					(if (!= (trunk x?) 319)
						(curRoom setScript: sInsertMeat)
					else
						(super doVerb: theVerb)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance trunkLid of Prop
	(properties
		x 299
		y 128
		noun 1
		approachX 258
		approachY 144
		view 631
		loop 2
		priority 10
		signal $4011
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(1
					(if (and (Btst 12) (trunkLid cel?))
						(theGame points: 1 147)
						(curRoom setInset: inBones)
					else
						(messager say: 1 1)
					)
				)
				(4
					(cond 
						((and (Btst 12) (not (trunkLid cel?)))
							(if (MuseumRgn nobodyAround:)
								(curRoom setScript: sOpenTrunk)
							else
								(return 1)
							)
						)
						((and (Btst 12) (trunkLid cel?)) (curRoom setScript: sCloseTrunk))
					)
				)
				(19
					(curRoom setScript: sInsertMeat)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance fridgeDoor of Prop
	(properties
		x 43
		y 99
		noun 4
		approachX 76
		approachY 146
		view 631
		signal $4001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (self cel?)
					(if (or (ego has: 9) (Btst 12))
						(messager say: 4 1 3)
					else
						(ego setMotion: PolyPath approachX approachY self)
					)
				else
					(messager say: 4 1 1)
				)
			)
			(4
				(if (not (curRoom script?))
					(if (self cel?)
						(curRoom setScript: sCloseFridge)
					else
						(curRoom setScript: sOpenFridge)
					)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (cue)
		(curRoom setInset: inMeat)
	)
)

(instance bag of View
	(properties
		x 21
		y 27
		noun 3
		view 630
		loop 3
		signal $4001
	)
)

(instance crate1 of View
	(properties
		x 138
		y 141
		noun 11
		view 630
		loop 6
		signal $4001
	)
)

(instance ferret of Actor
	(properties
		x 116
		y 151
		view 632
		loop 5
		signal $4000
		cycleSpeed 3
		xStep 4
		moveSpeed 3
	)
	
	(method (doit)
		(super doit:)
		(if (and (!= (trunk x?) 319) (not local0))
			(= local0 1)
			(self
				setScript: 0
				setLoop: 4
				setPri: -1
				cycleSpeed: 4
				moveSpeed: 6
				setCycle: Walk
				setMotion: MoveTo 158 164 self
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(6
				(switch (curRoom setInset: (ScriptID 20 0))
					(259
						(if (proc27_0 12 global298)
							(messager say: 1 6 1 0 0 1894)
						else
							(messager say: 1 6 4 0 0 1894)
							(proc27_1 12 @global298)
						)
					)
					(260
						(if (proc27_0 12 global299)
							(messager say: 1 6 1 0 0 1894)
						else
							(messager say: 1 6 5 0 0 1894)
							(proc27_1 12 @global299)
						)
					)
					(265
						(if (proc27_0 12 global304)
							(messager say: 1 6 1 0 0 1894)
						else
							(messager say: 1 6 10 0 0 1894)
							(proc27_1 12 @global304)
						)
					)
					(266
						(if (proc27_0 12 global305)
							(messager say: 1 6 1 0 0 1894)
						else
							(messager say: 1 6 11 0 0 1894)
							(proc27_1 12 @global305)
						)
					)
					(270
						(if (proc27_0 12 global309)
							(messager say: 1 6 1 0 0 1894)
						else
							(messager say: 1 6 15 0 0 1894)
							(proc27_1 12 @global309)
						)
					)
					(781
						(if (proc27_0 12 global333)
							(messager say: 1 6 1 0 0 1894)
						else
							(messager say: 1 6 39 0 0 1894)
							(proc27_1 12 @global333)
						)
					)
					(785
						(if (proc27_0 12 global337)
							(messager say: 1 6 1 0 0 1894)
						else
							(messager say: 1 6 43 0 0 1894)
							(proc27_1 12 @global337)
						)
					)
					(800
						(if (proc27_0 12 global352)
							(messager say: 1 6 1 0 0 1894)
						else
							(messager say: 1 6 58 0 0 1894)
							(proc27_1 12 @global352)
						)
					)
					(else 
						(messager say: 1 6 81 0 0 1894)
					)
				)
			)
			(41
				(messager say: 1 41 0 0 0 1894)
			)
			(8
				(messager say: 1 8 0 0 0 1894)
			)
			(19
				(messager say: 1 19 0 0 0 1894)
			)
			(24
				(messager say: 1 24 0 0 0 1894)
			)
			(25
				(messager say: 1 25 0 0 0 1894)
			)
			(23
				(messager say: 1 23 0 0 0 1894)
			)
			(2
				(messager say: 1 2 0 0 0 1894)
			)
			(4
				(messager say: 1 4 0 0 0 1894)
			)
			(1
				(messager say: 1 1 0 0 0 1894)
			)
			(else 
				(messager say: 1 0 0 0 0 1894)
			)
		)
	)
	
	(method (cue)
		(if local0
			(= local0 0)
			(self dispose:)
		else
			(self setScript: sFerretSniff)
		)
	)
)

(instance bugsWithMeat of Actor
	(properties
		x 275
		y 143
		view 631
		loop 3
		priority 11
		signal $4010
	)
)

(instance olympia of View
	(properties
		x 273
		y 151
		view 630
		loop 5
		signal $4001
	)
)

(instance snakeOil of View
	(properties
		x 288
		y 123
		z 20
		approachX 252
		approachY 152
		view 61
		priority 10
		signal $4011
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(messager say: 33 1 9 0 0 15)
			)
			(8
				(messager say: 33 8 0 0 0 15)
			)
			(4
				(ego get: 14)
				((ScriptID 21 0) doit: 783)
				(self dispose:)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance inMeat of Inset
	(properties
		view 630
		x 33
		y 66
		disposeNotOnMe 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(ego get: 9)
				((ScriptID 21 0) doit: 778)
				(self dispose:)
			)
			(1
				(messager say: 22 1 0 0 0 15)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance inBones of Inset
	(properties
		view 630
		loop 1
		x 258
		y 108
		disposeNotOnMe 1
		noun 2
	)
	
	(method (init)
		(super init: &rest)
		(LoadMany 132 4 6)
		(= local1 0)
		(curRoom setScript: sPlayMusic)
	)
	
	(method (dispose)
		(super dispose: &rest)
		(if (not local1) (theMusic2 fade: curRoom))
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(8
				(if (ego has: 7)
					(messager say: 2 8)
				else
					(= local1 1)
					(curRoom setInset: inWatch)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance inWatch of Inset
	(properties
		view 630
		loop 2
		x 259
		y 109
		disposeNotOnMe 1
		noun 22
	)
	
	(method (init)
		(super init: &rest)
		(= local1 0)
	)
	
	(method (dispose)
		(super dispose: &rest)
		(if (not local1) (theMusic2 fade: curRoom))
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(= local1 1)
				(curRoom setInset: inWatchOpen)
			)
			(1 (messager say: 22 1 4))
			(8 (messager say: 22 1 4))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance inWatchOpen of Inset
	(properties
		view 630
		loop 2
		cel 1
		x 259
		y 109
		disposeNotOnMe 1
		noun 22
	)
	
	(method (dispose param1)
		(super dispose:)
		(theMusic2 fade: curRoom)
		(return
			(if
				(and
					(== currentAct 3)
					argc
					param1
					(not (TriggerEvent 4880))
				)
				((ScriptID 22 0) doit: 4880 curRoom)
				(theGame points: 1 168)
				((ScriptID 90 2) moveTo: -2)
				(++ global111)
			else
				0
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(ego get: 7)
				((ScriptID 21 0) doit: 776)
				(theGame points: 1 148)
				(self dispose: 1)
			)
			(1
				(messager say: 26 8 0 0 0 15)
			)
			(8
				(messager say: 26 8 0 0 0 15)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance boxFrontLeft of Feature
	(properties
		x 61
		y 167
		noun 6
		nsTop 140
		nsBottom 194
		nsRight 122
		sightAngle 40
	)
)

(instance assortedBoxes of Feature
	(properties
		x 221
		y 170
		noun 7
		nsTop 152
		nsLeft 123
		nsBottom 189
		nsRight 319
		sightAngle 40
	)
)

(instance meatLocker of Feature
	(properties
		x 33
		y 92
		noun 8
		nsTop 49
		nsBottom 138
		nsRight 65
		sightAngle 40
	)
)

(instance leftShelves of Feature
	(properties
		x 75
		y 85
		noun 9
		nsTop 66
		nsLeft 65
		nsBottom 105
		nsRight 84
		sightAngle 40
	)
)

(instance cabinet of Feature
	(properties
		x 81
		y 125
		noun 10
		nsTop 112
		nsLeft 66
		nsBottom 138
		nsRight 94
		sightAngle 40
	)
)

(instance crate2 of Feature
	(properties
		x 190
		y 115
		noun 12
		nsTop 87
		nsLeft 163
		nsBottom 142
		nsRight 218
		sightAngle 40
	)
)

(instance crate3 of Feature
	(properties
		x 176
		y 79
		noun 13
		nsTop 71
		nsLeft 164
		nsBottom 87
		nsRight 187
		sightAngle 40
	)
)

(instance poster of Feature
	(properties
		x 98
		y 93
		noun 14
		nsTop 83
		nsLeft 88
		nsBottom 103
		nsRight 106
		sightAngle 40
	)
)

(instance assortedTools of Feature
	(properties
		x 234
		y 104
		noun 15
		nsTop 94
		nsLeft 220
		nsBottom 114
		nsRight 246
		sightAngle 40
	)
)

(instance canister of Feature
	(properties
		x 247
		y 133
		noun 16
		nsTop 122
		nsLeft 239
		nsBottom 140
		nsRight 254
		sightAngle 40
	)
)

(instance light of Feature
	(properties
		x 293
		y 68
		noun 17
		nsTop 69
		nsLeft 283
		nsBottom 77
		nsRight 303
		sightAngle 40
	)
)

(instance rightShelf of Feature
	(properties
		x 299
		y 84
		noun 18
		nsTop 77
		nsLeft 280
		nsBottom 96
		nsRight 319
		sightAngle 40
	)
)

(instance desk of Feature
	(properties
		x 298
		y 110
		noun 19
		nsTop 102
		nsLeft 277
		nsBottom 119
		nsRight 319
		sightAngle 40
	)
)

(instance sFX of Sound
	(properties
		flags $0001
	)
)

(instance sFX2 of Sound
	(properties
		flags $0001
	)
)
