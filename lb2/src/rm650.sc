;;; Sierra Script 1.0 - (do not remove this comment)
(script# 650)
(include game.sh) (include "650.shm")
(use Main)
(use LBRoom)
(use ExitFeature)
(use MuseumRgn)
(use Inset)
(use Scaler)
(use Osc)
(use PolyPath)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm650 0
)

(local
	local0
	local1
	local2
)
(instance rm650 of LBRoom
	(properties
		noun N_ROOM
		picture 650
		north 666
		south 600
		vanishingY -30
	)
	
	(method (init)
		(LoadMany RES_VIEW 652 654)
		(ego init: normalize: 831 setScale: Scaler 140 0 190 -30)
		(self setRegions: 90)
		(switch prevRoomNum
			(south (ego x: 160))
			(else 
				(ego wearingGown: 1 posn: 180 160)
				(theGame handsOn:)
			)
		)
		(super init:)
		(LoadMany RES_SOUND 651 652 655 49 721)
		(theMusic2 number: 650 flags: 1 loop: -1 play:)
		(chair init:)
		(shelf init:)
		(mace init:)
		(maceChain init:)
		(leftWeapon init:)
		(medalsA init:)
		(medalsB init:)
		(desk init: setOnMeCheck: 1 4)
		(light init: setOnMeCheck: 1 16)
		(chairWolf init: setOnMeCheck: 1 8)
		(lamp init:)
		(blotter init: setOnMeCheck: 1 32)
		(intercom init:)
		(shelf1 init: setOnMeCheck: 1 64 approachVerbs: 1 4)
		(shelf2 init: setOnMeCheck: 1 128 approachVerbs: 1 4)
		(shelf3 init: approachVerbs: 1 4)
		(shelf4 init: approachVerbs: 1 4)
		(shelf5 init: approachVerbs: 1 4)
		(shelf6a init: approachVerbs: 1 4)
		(shelf6b init: approachVerbs: 1 4)
		(shelf6c init: approachVerbs: 1 4)
		(shelf6d init: approachVerbs: 1 4)
		(shelf7 init: setOnMeCheck: 1 512 approachVerbs: 1 4)
		(shelf8 init: setOnMeCheck: 1 256 approachVerbs: 1 4)
		(weapons1 init:)
		(weapons2 init:)
		(weapons3 init:)
		(weapons4 init:)
		(weaponsB init:)
		(secretShelf init:)
		(if (Btst 117)
			(secretPanel
				approachVerbs: 4
				setCel: (secretPanel lastCel:)
				init:
			)
		else
			(secretPanel init: approachVerbs: 4)
		)
		(trap addToPic:)
		(sword init:)
		(string1 init: approachVerbs: 22 21)
		(string2 init: approachVerbs: 22 21)
		(string3 init: approachVerbs: 22 21)
		(if (Btst 11)
			(gun init:)
			(gunFire setCel: (gunFire lastCel:) init:)
			(string1 setCel: (string1 lastCel:))
			(string2 setCel: (string2 lastCel:))
			(string3 setCel: (string3 lastCel:))
		else
			(LoadMany 132 653 654)
		)
		(southExit init:)
		(eastExit init:)
		(if
		(and (Random 0 1) (== currentAct 4) (ego has: 16))
			((ScriptID 32 0)
				init:
				view: 814
				loop: 8
				cel: 2
				actions: talkActions
				room: 650
				posn: 188 120
			)
		else
			((ScriptID 32 0) moveTo: -2)
		)
		(cond 
			(
				(and
					(== currentAct 3)
					(not (Btst 57))
					(not (TriggerEvent 8224))
				)
				(if (== ((ScriptID 90 2) room?) curRoomNum)
					((ScriptID 90 2) goTo: 520)
				)
				(string1 setScript: sIntercom)
			)
			(
				(and
					(== ((ScriptID 90 2) room?) curRoomNum)
					(not (ego has: 17))
				)
				((ScriptID 90 2) goTo: 520)
			)
		)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			((IsObjectOnControl ego 2) (self setScript: sExitToSouth))
		)
	)
	
	(method (dispose)
		(theMusic2 fade:)
		(super dispose:)
	)
	
	(method (newRoom)
		(super newRoom: &rest)
		(if (== newRoomNum north) (Bclr 117))
	)
)

(instance sExitToSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 330 (ego y?) self)
			)
			(1
				(curRoom newRoom: (curRoom south?))
			)
		)
	)
)

(instance sIntercom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1 (= seconds 30))
			(2
				(if (curRoom script?)
					(-- state)
					(= seconds 5)
				else
					(messager say: 40)
					(= cycles 1)
				)
			)
			(3 (Bset 57) (self dispose:))
		)
	)
)

(instance sTouchTrap of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 179 157 self)
			)
			(1
				(ego
					view: 652
					setLoop: 2
					setCel: 0
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(2
				(theMusic2 stop:)
				(sFX number: 654 flags: 1 loop: 1 play:)
				(string1 setCycle: EndLoop self)
			)
			(3
				(string1 stopUpd:)
				(string2 setCycle: EndLoop self)
			)
			(4
				(string2 stopUpd:)
				(string3 setCycle: EndLoop self)
			)
			(5
				(string3 stopUpd:)
				(gun init:)
				(= ticks 60)
			)
			(6
				(gun setCycle: Forward)
				(sFX number: 653 flags: 1 loop: -1 play:)
				(gunFire init: cycleSpeed: 10 setCycle: EndLoop)
				(ego
					setLoop: 3
					setCel: 0
					setMotion: MoveTo (+ (ego x?) 7) (+ (ego y?) 3)
					setCycle: EndLoop self
				)
			)
			(7 (= ticks 60))
			(8
				(sFX stop:)
				(gun setCycle: 0 setCel: 0)
				(= ticks 120)
			)
			(9
				(= deathReason 6)
				(curRoom newRoom: 99)
				(self dispose:)
			)
		)
	)
)

(instance sDisarmTrap of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (== register 30)
					(= temp0 206)
					(= temp1 152)
				else
					(= temp0 203)
					(= temp1 150)
				)
				(ego setMotion: PolyPath temp0 temp1 self)
			)
			(1 (ego setHeading: 270 self))
			(2
				(ego
					view: 652
					setLoop: (if (== register 30) 0 else 1)
					setCel: 0
					setPri: 13
					cycleSpeed: 12
					setScale: Scaler 100 100 190 -30
					setCycle: EndLoop self
				)
			)
			(3
				(sFX number: 654 flags: 1 loop: 1 play:)
				(ego
					normalize: 831
					loop: 1
					setScale: Scaler 140 0 190 -30
				)
				(string1 setCycle: EndLoop self)
			)
			(4
				(string1 stopUpd:)
				(string2 setCycle: EndLoop self)
			)
			(5
				(string2 stopUpd:)
				(string3 setCycle: EndLoop self)
			)
			(6
				(string3 stopUpd:)
				(gun init:)
				(= ticks 60)
			)
			(7
				(gun setCycle: Forward)
				(sFX number: 653 flags: 1 loop: -1 play:)
				(gunFire init: cycleSpeed: 10 setCycle: EndLoop)
				(= ticks 180)
			)
			(8
				(sFX stop:)
				(gun setCycle: 0 setCel: 0)
				(Bset 11)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGetCheese of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 179 157 self)
			)
			(1
				(ego
					view: 652
					setScale: Scaler 100 100 190 -30
					setLoop: 2
					setCel: 0
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(2
				(ego get: 16)
				((ScriptID 21 0) doit: 785)
				(theGame points: 1 152)
				(= cycles 1)
			)
			(3
				(ego view: 652 setLoop: 2 setCel: 0 setCycle: BegLoop self)
			)
			(4
				(ego
					normalize: 831
					setScale: Scaler 140 0 190 -30
					loop: 1
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sLookTrap of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (< (ego x?) 169)
					(ego setMotion: PolyPath 153 163 self)
				else
					(ego setMotion: PolyPath 186 154 self)
				)
			)
			(1 (Face ego trap self))
			(2
				(curRoom setInset: inCheese)
				(self dispose:)
			)
		)
	)
)

(instance sSwordKill of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 282 130 self)
			)
			(1
				(ego
					view: 652
					setLoop: 4
					setCel: 0
					cycleSpeed: 10
					setCycle: EndLoop self
				)
			)
			(2
				(theMusic2 stop:)
				(sFX number: 655 flags: 1 loop: 1 play:)
				(ego setLoop: 5 setCel: 0 setCycle: CycleTo 5 1 self)
				(sword dispose:)
			)
			(3
				(sFX number: 652 flags: 1 loop: 1 play:)
				(ego setCycle: EndLoop self)
			)
			(4 (= ticks 150))
			(5
				(= deathReason 13)
				(curRoom newRoom: 99)
				(self dispose:)
			)
		)
	)
)

(instance sShocked of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(switch register
					(0 (= temp0 200) (= temp1 109))
					(1 (= temp0 212) (= temp1 112))
					(2 (= temp0 227) (= temp1 122))
					(3 (= temp0 250) (= temp1 123))
					(4 (= temp0 298) (= temp1 134))
				)
				(ego setMotion: PolyPath temp0 temp1 self)
			)
			(1
				(ego
					view: 654
					setLoop: 0
					setCel: 0
					cycleSpeed: 4
					setCycle: CycleTo 4 1 self
				)
			)
			(2
				(theMusic2 stop:)
				(= cycles 2)
			)
			(3
				(sFX number: 651 flags: 1 play:)
				(ego setCycle: Oscillate 1 self)
			)
			(4 (sFX stop:) (= ticks 30))
			(5
				(ego
					setLoop: 1
					setCel: 0
					cycleSpeed: 10
					setCycle: EndLoop self
				)
			)
			(6 (= ticks 150))
			(7 (ego setCycle: BegLoop self))
			(8
				(ego normalize: 831 loop: 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sShockedWire of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 652
					setLoop: 2
					setCel: 0
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(1
				(ego
					view: 654
					setLoop: 0
					setCel: 4
					cycleSpeed: 4
					setCycle: EndLoop self
				)
				(sFX number: 651 flags: 1 loop: -1 play:)
			)
			(2 (ego setCycle: CycleTo 4 -1 self))
			(3
				(sFX stop:)
				(ego
					view: 652
					setLoop: 2
					setCel: (ego lastCel:)
					cycleSpeed: 12
				)
				(= ticks 15)
			)
			(4
				(messager say: 35 21 0 0 self)
			)
			(5 (ego setCycle: BegLoop self))
			(6
				(ego normalize: 831 loop: 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sShockedWire3 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 654
					setLoop: 0
					setCel: 0
					cycleSpeed: 4
					setCycle: Oscillate 1 self
				)
				(sFX number: 651 flags: 1 loop: -1 play:)
			)
			(1 (sFX stop:) (= ticks 30))
			(2
				(ego
					setLoop: 1
					setCel: 0
					cycleSpeed: 10
					setCycle: EndLoop self
				)
			)
			(3
				(messager say: 35 21 0 0 self)
			)
			(4 (ego setCycle: BegLoop self))
			(5
				(ego normalize: 831 loop: 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEnterSecretPassage of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego view: 652 setLoop: 4 setCel: 0 setCycle: CycleTo 3 1 self)
			)
			(1
				(sFX number: 49 flags: 1 loop: 1 play: self)
			)
			(2
				(ego
					normalize: 831
					loop: 0
					setScale: Scaler 140 0 190 -30
				)
				(secretShelf
					setLoop: 6
					setMotion: MoveTo 161 (secretShelf y?) self
				)
				(sFX number: 721 flags: 1 loop: -1 play:)
			)
			(3
				(sFX stop:)
				(secretShelf stopUpd:)
				(ego setMotion: PolyPath 132 107 self)
			)
			(4
				(ego setMotion: MoveTo 126 99 self)
			)
			(5
				(curRoom newRoom: (curRoom north?))
				(self dispose:)
			)
		)
	)
)

(instance sShortPause of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 60))
			(1
				(messager say: 32 4)
				(self dispose:)
			)
		)
	)
)

(instance secretShelf of Actor
	(properties
		x 137
		y 15
		z -89
		view 650
		loop 6
		signal $4001
	)
)

(instance secretPanel of Prop
	(properties
		x 199
		y 80
		approachX 196
		approachY 118
		view 650
		loop 7
		signal $4001
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(1
					(if cel (messager say: 43 1) else (messager say: 6 1))
				)
				(4
					(if (MuseumRgn nobodyAround:)
						(sFX number: 49 flags: 1 loop: 1 play:)
						(if cel
							(secretButton dispose:)
							(self setCycle: BegLoop self)
							(Bclr 117)
						else
							(secretButton init: approachVerbs: 4)
							(self setCycle: EndLoop self)
							(Bset 117)
						)
					else
						(return 1)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
	
	(method (cue)
		(sFX stop:)
		(self stopUpd:)
	)
)

(instance gun of Prop
	(properties
		x 46
		y 91
		z 50
		noun 28
		view 651
		signal $0001
	)
)

(instance string1 of Prop
	(properties
		x 149
		y 144
		noun 35
		approachX 161
		approachY 150
		view 650
		loop 4
		signal $4001
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(22
					(if (MuseumRgn nobodyAround:)
						(curRoom setScript: sShockedWire)
					else
						(return 1)
					)
				)
				(21
					(if (MuseumRgn nobodyAround:)
						(curRoom setScript: sShockedWire)
					else
						(return 1)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance string2 of Prop
	(properties
		x 81
		y 111
		noun 35
		approachX 92
		approachY 114
		view 650
		loop 3
		signal $4001
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(22
					(if (MuseumRgn nobodyAround:)
						(curRoom setScript: sShockedWire)
					else
						(return 1)
					)
				)
				(21
					(if (MuseumRgn nobodyAround:)
						(curRoom setScript: sShockedWire)
					else
						(return 1)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance string3 of Prop
	(properties
		x 60
		y 57
		noun 35
		approachX 55
		approachY 114
		view 650
		loop 3
		signal $4001
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(22
					(if (MuseumRgn nobodyAround:)
						(curRoom setScript: sShockedWire3)
					else
						(return 1)
					)
				)
				(21
					(if (MuseumRgn nobodyAround:)
						(curRoom setScript: sShockedWire3)
					else
						(return 1)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance gunFire of Prop
	(properties
		x 150
		y 143
		view 652
		loop 7
		signal $4000
	)
)

(instance trap of View
	(properties
		x 165
		y 157
		view 651
		loop 1
		signal $4001
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(1
					(if (not (ego has: 16))
						(curRoom setScript: sLookTrap)
					else
						(messager say: 27 1 2)
					)
				)
				(8 (self doVerb: 1))
				(4
					(cond 
						((ego has: 16) (messager say: 27 4 2))
						((Btst 11)
							(if (MuseumRgn nobodyAround:)
								(curRoom setScript: sGetCheese)
							else
								(return 1)
							)
						)
						((MuseumRgn nobodyAround:) (curRoom setScript: sTouchTrap))
						(else (return 1))
					)
				)
				(29
					(cond 
						((Btst 11)
							(if (ego has: 16)
								(messager say: 27 4 2)
							else
								(messager say: 27 4 7)
							)
						)
						((MuseumRgn nobodyAround:) (curRoom setScript: sDisarmTrap 0 29))
						(else (return 1))
					)
				)
				(30
					(cond 
						((Btst 11) (messager say: 27 4 7))
						((MuseumRgn nobodyAround:) (curRoom setScript: sDisarmTrap 0 30))
						(else (return 1))
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance sword of View
	(properties
		x 287
		y 40
		noun 25
		view 652
		loop 6
		signal $4001
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(if (MuseumRgn nobodyAround:)
						(curRoom setScript: sSwordKill)
					else
						(return 1)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance inCheese of Inset
	(properties
		view 650
		loop 2
		x 147
		y 135
		disposeNotOnMe 1
		noun 33
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(1 (messager say: 33 1 5))
				(8 (messager say: 5 8 0 0 0 15))
				(4
					(cond 
						((Btst 11)
							(if (MuseumRgn nobodyAround:)
								(curRoom setScript: sGetCheese)
							else
								(return 1)
							)
						)
						((MuseumRgn nobodyAround:) (curRoom setScript: sTouchTrap))
						(else (return 1))
					)
					(self dispose:)
				)
				(29
					(cond 
						((Btst 11) (messager say: 27 4 2))
						((MuseumRgn nobodyAround:) (curRoom setScript: sDisarmTrap 0 29))
						(else (return 1))
					)
					(self dispose:)
				)
				(30
					(cond 
						((Btst 11) (messager say: 27 4 2))
						((MuseumRgn nobodyAround:) (curRoom setScript: sDisarmTrap 0 30))
						(else (return 1))
					)
					(self dispose:)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance inBooks of Inset
	(properties
		view 650
		loop 1
		disposeNotOnMe 1
		noun 29
	)
	
	(method (init)
		(= x (- local0 24))
		(= y (- local1 20))
		(if (== local2 6) (= loop 0) else (= loop 1))
		(super init: &rest)
		(if (== local2 6)
			(poetryBook
				init:
				nsLeft: (+ x 7)
				nsTop: (+ y 10)
				nsBottom: (+ y 28)
				nsRight: (+ x 13)
			)
		)
		(if (== local2 7)
			(heimlichBook
				init:
				nsLeft: (+ x 19)
				nsTop: (+ y 10)
				nsBottom: (+ y 28)
				nsRight: (+ x 25)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(messager say: (+ 13 local2) 1)
			)
			(4 (messager say: 29 4))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance inClosedBook of Inset
	(properties
		view 650
		loop 5
		cel 1
		x 88
		y 65
		disposeNotOnMe 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (messager say: 32 1))
			(4
				(curRoom setInset: inOpenBook)
			)
			(8 (messager say: 32 8))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance inOpenBook of Inset
	(properties
		view 650
		loop 5
		x 90
		y 67
		disposeNotOnMe 1
	)
	
	(method (init)
		(super init: &rest)
		(curRoom setScript: sShortPause)
	)
	
	(method (dispose param1)
		(super dispose:)
		(if (and argc param1)
			(ego get: 17)
			((ScriptID 21 0) doit: 786)
			(theGame points: 1 153)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (curRoom setInset: inGarter))
			(8 (curRoom setInset: inGarter))
			(4 (self dispose: 1))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance inGarter of Inset
	(properties
		view 650
		loop 2
		cel 1
		x 92
		y 69
		disposeNotOnMe 1
	)
	
	(method (dispose param1)
		(super dispose:)
		(if (and argc param1)
			(ego get: 17)
			((ScriptID 21 0) doit: 786)
			(theGame points: 1 153)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(messager say: 12 1 0 0 0 15)
			)
			(8
				(messager say: 12 8 0 0 0 15)
			)
			(4 (self dispose: 1))
			(else  (super doVerb: theVerb))
		)
	)
)

(class MyFeature of Feature
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		modNum -1
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
	)
	
	(method (onMe theObjOrX theY &tmp temp0 temp1)
		(if (IsObject theObjOrX)
			(= temp0 (theObjOrX x?))
			(= temp1 (theObjOrX y?))
		else
			(= temp0 theObjOrX)
			(= temp1 theY)
		)
		(= local0 temp0)
		(= local1 temp1)
		(return
			(cond 
				((IsObject onMeCheck) (AvoidPath temp0 temp1 onMeCheck))
				(
					(or
						(not (if (or nsLeft nsRight nsTop) else nsBottom))
						(and
							(<= nsLeft temp0)
							(<= temp0 nsRight)
							(<= nsTop temp1)
							(<= temp1 nsBottom)
						)
					)
					(if (!= onMeCheck 26505)
						(& onMeCheck (OnControl 4 temp0 temp1))
					else
						1
					)
				)
			)
		)
	)
)

(instance secretButton of Feature
	(properties
		x 193
		y 81
		z 10
		noun 44
		nsTop 66
		nsLeft 189
		nsBottom 77
		nsRight 198
		approachX 188
		approachY 110
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(if (MuseumRgn nobodyAround:)
						(theGame handsOff:)
						(curRoom setScript: sEnterSecretPassage)
					else
						(return 1)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance poetryBook of Feature
	(properties
		y 200
	)
	
	(method (initialize)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(1
					(if (ego has: 17)
						(messager say: 32 1 3)
						(inBooks dispose:)
					else
						(messager say: 32 1 6)
					)
				)
				(4
					(cond 
						((ego has: 17) (messager say: 32 4 3) (inBooks dispose:))
						((MuseumRgn nobodyAround:) (curRoom setInset: inClosedBook))
						(else (return 1))
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance heimlichBook of Feature
	(properties
		y 200
		noun 31
	)
)

(instance chair of Feature
	(properties
		x 216
		y 161
		noun 1
		nsTop 146
		nsLeft 201
		nsBottom 176
		nsRight 232
		sightAngle 40
	)
)

(instance shelf of Feature
	(properties
		x 24
		y 139
		noun 2
		nsTop 90
		nsBottom 189
		nsRight 49
		sightAngle 40
	)
)

(instance mace of Feature
	(properties
		x 12
		y 68
		noun 3
		nsTop 52
		nsBottom 84
		nsRight 24
		sightAngle 40
	)
)

(instance maceChain of Feature
	(properties
		x 4
		y 26
		noun 3
		nsTop -1
		nsBottom 53
		nsRight 9
		sightAngle 40
	)
)

(instance leftWeapon of Feature
	(properties
		x 21
		y 28
		noun 4
		nsLeft 9
		nsBottom 56
		nsRight 33
		sightAngle 40
	)
)

(instance medalsA of Feature
	(properties
		x 51
		y 54
		noun 5
		nsTop 36
		nsLeft 43
		nsBottom 73
		nsRight 60
		sightAngle 40
	)
)

(instance medalsB of Feature
	(properties
		x 187
		y 65
		noun 6
		nsTop 51
		nsLeft 177
		nsBottom 75
		nsRight 197
		sightAngle 40
	)
)

(instance desk of Feature
	(properties
		x 140
		y 127
		noun 7
		nsTop 106
		nsLeft 83
		nsBottom 148
		nsRight 198
		sightAngle 40
	)
)

(instance light of Feature
	(properties
		x 160
		y 25
		noun 8
		nsTop -1
		nsLeft 144
		nsBottom 51
		nsRight 179
		sightAngle 40
	)
)

(instance chairWolf of Feature
	(properties
		x 122
		y 120
		z 27
		noun 9
		nsTop 87
		nsLeft 111
		nsBottom 100
		nsRight 134
		sightAngle 40
	)
)

(instance lamp of Feature
	(properties
		x 114
		y 120
		z 18
		noun 10
		nsTop 95
		nsLeft 108
		nsBottom 109
		nsRight 121
		sightAngle 40
	)
)

(instance intercom of Feature
	(properties
		x 168
		y 120
		z 22
		noun 11
		nsTop 94
		nsLeft 160
		nsBottom 102
		nsRight 176
		sightAngle 40
	)
)

(instance blotter of Feature
	(properties
		x 142
		y 120
		z 18
		noun 12
		nsTop 96
		nsLeft 121
		nsBottom 109
		nsRight 163
		sightAngle 40
	)
)

(instance shelf1 of MyFeature
	(properties
		x 112
		y 27
		noun 13
		nsTop 20
		nsLeft 69
		nsBottom 35
		nsRight 156
		sightAngle 40
		approachX 123
		approachY 115
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(= local2 0)
				(curRoom setInset: inBooks)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance shelf2 of MyFeature
	(properties
		x 112
		y 37
		noun 14
		nsTop 31
		nsLeft 69
		nsBottom 44
		nsRight 156
		sightAngle 40
		approachX 123
		approachY 115
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(= local2 1)
				(curRoom setInset: inBooks)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance shelf3 of MyFeature
	(properties
		x 106
		y 47
		noun 15
		nsTop 42
		nsLeft 69
		nsBottom 52
		nsRight 144
		sightAngle 40
		approachX 123
		approachY 115
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(= local2 2)
				(curRoom setInset: inBooks)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance shelf4 of MyFeature
	(properties
		x 112
		y 57
		noun 16
		nsTop 52
		nsLeft 69
		nsBottom 63
		nsRight 156
		sightAngle 40
		approachX 123
		approachY 115
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(= local2 3)
				(curRoom setInset: inBooks)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance shelf5 of MyFeature
	(properties
		x 112
		y 68
		noun 17
		nsTop 64
		nsLeft 69
		nsBottom 72
		nsRight 156
		sightAngle 40
		approachX 123
		approachY 115
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(= local2 4)
				(curRoom setInset: inBooks)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance shelf6a of MyFeature
	(properties
		x 84
		y 81
		noun 18
		nsTop 76
		nsLeft 70
		nsBottom 86
		nsRight 98
		sightAngle 40
		approachX 123
		approachY 115
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(= local2 5)
				(curRoom setInset: inBooks)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance shelf6b of MyFeature
	(properties
		x 107
		y 80
		noun 19
		nsTop 76
		nsLeft 98
		nsBottom 85
		nsRight 117
		sightAngle 40
		approachX 123
		approachY 115
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(= local2 6)
				(curRoom setInset: inBooks)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance shelf6c of MyFeature
	(properties
		x 127
		y 78
		noun 20
		nsTop 74
		nsLeft 119
		nsBottom 83
		nsRight 135
		sightAngle 40
		approachX 123
		approachY 115
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(= local2 7)
				(curRoom setInset: inBooks)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance shelf6d of MyFeature
	(properties
		x 145
		y 78
		noun 21
		nsTop 74
		nsLeft 136
		nsBottom 83
		nsRight 154
		sightAngle 40
		approachX 123
		approachY 115
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(= local2 8)
				(curRoom setInset: inBooks)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance shelf7 of MyFeature
	(properties
		x 111
		y 89
		noun 22
		nsTop 82
		nsLeft 68
		nsBottom 96
		nsRight 155
		sightAngle 40
		approachX 123
		approachY 115
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(= local2 9)
				(curRoom setInset: inBooks)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance shelf8 of MyFeature
	(properties
		x 111
		y 99
		noun 23
		nsTop 89
		nsLeft 68
		nsBottom 110
		nsRight 155
		sightAngle 40
		approachX 123
		approachY 115
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(= local2 10)
				(curRoom setInset: inBooks)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance weapons1 of Feature
	(properties
		x 241
		y 60
		noun 24
		nsTop 43
		nsLeft 200
		nsBottom 69
		nsRight 217
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(if (MuseumRgn nobodyAround:)
						(curRoom setScript: sShocked 0 0)
					else
						(return 1)
					)
				)
				(8 (messager say: 39 8))
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance weapons2 of Feature
	(properties
		x 21
		y 28
		noun 36
		nsTop 40
		nsLeft 215
		nsBottom 62
		nsRight 230
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(if (MuseumRgn nobodyAround:)
						(curRoom setScript: sShocked 0 1)
					else
						(return 1)
					)
				)
				(8 (messager say: 39 8))
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance weapons3 of Feature
	(properties
		x 21
		y 28
		noun 37
		nsTop 37
		nsLeft 231
		nsBottom 83
		nsRight 245
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(if (MuseumRgn nobodyAround:)
						(curRoom setScript: sShocked 0 2)
					else
						(return 1)
					)
				)
				(8 (messager say: 39 8))
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance weapons4 of Feature
	(properties
		x 21
		y 28
		noun 38
		nsTop 36
		nsLeft 246
		nsBottom 73
		nsRight 278
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(if (MuseumRgn nobodyAround:)
						(curRoom setScript: sShocked 0 3)
					else
						(return 1)
					)
				)
				(8 (messager say: 39 8))
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance weaponsB of Feature
	(properties
		x 305
		y 53
		noun 26
		nsTop 27
		nsLeft 297
		nsBottom 115
		nsRight 314
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(if (MuseumRgn nobodyAround:)
						(curRoom setScript: sShocked 0 4)
					else
						(return 1)
					)
				)
				(8 (messager say: 39 8))
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance southExit of ExitFeature
	(properties
		nsTop 185
		nsLeft 46
		nsBottom 189
		nsRight 319
		cursor 11
		exitDir 3
		noun 42
	)
)

(instance eastExit of ExitFeature
	(properties
		nsTop 132
		nsLeft 315
		nsBottom 179
		nsRight 319
		cursor 14
		exitDir 2
		noun 42
	)
)

(instance sFX of Sound
	(properties
		flags $0001
	)
)

(instance talkActions of Actions
	(properties)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(2
					(messager say: 3 0 88 1 0 1889)
					1
				)
				(else  (return 0))
			)
		)
	)
)
