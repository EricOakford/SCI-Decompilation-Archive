;;; Sierra Script 1.0 - (do not remove this comment)
(script# BRAIN) ;704
(include game.sh)
(use Main)
(use RegionPath)
(use Sq4Dialog)
(use Sq4Feature)
(use PolyPath)
(use Polygon)
(use MoveFwd)
(use LoadMany)
(use StopWalk)
(use Window)
(use Sound)
(use Motion)
(use Game)
(use System)

(public
	brain 0
	droid1 1
	droid2 2
	terminal1 3
	terminal2 4
	terminal3 5
	WarningPrint 6
	timerSound 7
	DisplayCountdown 8
)

(local
	local0
	local1
	egoX
	local3
	local4
	local5
	local6
	local7
	local8
	theRegister
	local10
	local11
	[local12 100]
	[local112 100]
	[local212 40]
	[droidPoly 271] = [32767 515 23 170 166 170 207 124 340 124 32767 520 -20 124 53 124 104 143 203 143 255 124 340 124 32767 525 -20 124 69 124 -20 -18 32767 510 158 240 106 161 -20 161 32767 505 340 163 65 163 255 163 271 240 32767 520 230 130 230 172 92 172 49 152 -20 152 32767 515 340 154 135 154 117 166 79 166 61 73 96 73 177 -8 32767 500 63 240 173 120 340 120 32767 505 -20 124 44 124 78 31 44 124 -20 124 32767 500 340 120 173 120 63 240 32767 515 177 -18 136 34 340 34 32767 520 -20 34 91 34 111 22 204 22 233 34 340 34 32767 525 -20 34 140 34 95 -18 32767 510 267 240 147 120 -20 120 32767 505 340 124 271 124 236 31 271 124 340 124 32767 510 -20 120 147 120 267 240 32767 525 105 -18 140 34 227 34 340 111 340 180 203 180 124 91 -20 91 32767 520 340 91 241 91 209 103 103 103 81 91 -20 91 32767 515 340 91 77 91 96 73 61 73 79 166 117 166 135 154 340 154 32767 520 -20 152 78 152 78 124 32767 505 42 240 65 163 340 163 32767 510 -20 161 106 161 158 240 32767 525 -20 -18 69 124 -20 124 32767 520 340 124 255 124 203 143 104 143 53 124 -20 124 32767 515 340 124 207 124 166 170 23 170 -32768]
	theSpeed
)
(procedure (WarningPrint)
	(if (== curRoomNum newRoomNum)
		(voiceWindow color: myTextColor13 back: myLowlightColor)
		(SQ4Print
			&rest
			#window voiceWindow
			#title {** WARNING **}
			#mode teJustCenter
			#at 160 180
			#width 150
			#time 4
			#dispose
		)
	)
)

(procedure (DisplayCountdown &tmp brainFormatting)
	(switch (= brainFormatting (brain formatting?))
		(5000
			(StrCpy @local212 {EXCELLENT})
			(= local10 myTextColor7)
		)
		(3000
			(StrCpy @local212 {VERY GOOD})
		)
		(2000 (StrCpy @local212 {GOOD}))
		(1500 (StrCpy @local212 {FAIR}))
		(1000
			(StrCpy @local212 {DANGER})
			(= local10 myTextColor5)
		)
		(500
			(StrCpy @local212 {CRITICAL})
			(= local10 myTextColor13)
		)
	)
	(if
		(and
			(< 0 brainFormatting)
			(< brainFormatting 3000)
			(not (mod brainFormatting 250))
		)
		(WarningPrint
			(Format @local112 BRAIN 0 brainFormatting @local212)
		)
		(if (== (DoAudio 6) -1) (alertSound play:))
	)
	(if
		(and
			brainFormatting
			(!= curRoomNum 544)
			(!= (curRoom curPic?) 699)
		)
		(if (or (not local12) formatTimeStopped)
			(Format @local12 {Time to Format:})
			(DoDisplay @local12 64 0 180 67 100 25 local10)
			(= formatTimeStopped FALSE)
		)
		(Format @local12 BRAIN 1 brainFormatting)
		(if local11 (DoDisplay local11))
		(if (== newRoomNum curRoomNum)
			(= local11
				(DoDisplay @local12 64 90 180 67 25 25 local10)
			)
		)
	)
)

(procedure (localproc_017a param1 &tmp temp0 temp1)
	(cond 
		((< (= temp0 ((param1 mover?) value?)) 50) (= temp1 2))
		((< temp0 72) (= temp1 1))
		((< temp0 208) (= temp1 3))
		((< temp0 224) (= temp1 1))
		(else (= temp1 2))
	)
	(return temp1)
)

(procedure (localproc_036b param1)
	(return
		(cond 
			((!= (param1 level?) (brain level?)) 0)
			((Btst 38) 0)
			((== (param1 lastRoom?) curRoomNum) 0)
			((== (param1 room?) (curRoom north?)) 1)
			((== (param1 room?) (curRoom south?)) 3)
			((== (param1 room?) (curRoom east?)) 2)
			((== (param1 room?) (curRoom west?)) 4)
			(else 0)
		)
	)
)

(procedure (localproc_0408 param1)
	(return
		(cond 
			(
			(or (< (ego heading?) 45) (> (ego heading?) 305))
				(switch param1
					(1 (return 0))
					(3 (return 1))
					(2 (return 2))
					(4 (return 3))
				)
			)
			((< (ego heading?) 135)
				(switch param1
					(1 (return 3))
					(3 (return 2))
					(2 (return 0))
					(4 (return 1))
				)
			)
			((< (ego heading?) 235)
				(switch param1
					(1 (return 1))
					(3 (return 0))
					(2 (return 3))
					(4 (return 2))
				)
			)
			(else
				(switch param1
					(1 (return 2))
					(3 (return 3))
					(2 (return 1))
					(4 (return 0))
				)
			)
		)
	)
)

(procedure (localproc_04fc param1 &tmp [temp0 40])
	(switch (localproc_0408 param1)
		(0
			(narrator modNum: BRAIN say: 1)
		)
		(1
			(narrator modNum: BRAIN say: 2)
		)
		(3
			(narrator modNum: BRAIN say: 3)
		)
		(2
			(narrator modNum: BRAIN say: 4)
		)
	)
	(globalSound
		number: 845
		vol: 0
		loop: -1
		play:
		fade: 127 1 10 0
	)
)

(instance voiceWindow of SysWindow
	(properties)
)

(class brain of Region
	(properties
		level 1
		formatting 0
		lastRoom 0
		exitDir 0
	)
	
	(method (init &tmp temp0 temp1 temp2)
		(LoadMany VIEW 501 28 506)
		(= theSpeed speed)
		(super init: &rest)
		(if (and (not (Btst fDeletedDroids)) (!= curRoomNum 541))
			(droid1 init:)
			(droid2 init:)
		)
		(switch level
			(1 (= temp0 2))
			(2 (= temp0 3))
			(3 (= temp0 -1))
		)
		(if (== curRoomNum 541)
			(ego
				view: 0
				setCycle: StopWalk 4
				headView: 4
				illegalBits: 0
				setPri: temp0
				normal: 1
			)
		else
			(ego
				view: 3
				setCycle: StopWalk 510
				headView: 510
				illegalBits: 0
				setPri: temp0
				normal: 1
			)
		)
		(= local12 0)
		(cond 
			((OneOf curRoomNum 541 544) (= temp2 0))
			((brain formatting?) (= temp2 869))
			((== curRoomNum 520) (= temp2 849))
			((== curRoomNum 514) (= temp2 850))
			(else (= temp2 20))
		)
		(cond 
			((not temp2) (music fade:))
			((!= (music number?) temp2) (music number: temp2 vol: 127 loop: -1 playBed:))
		)
		(if (= temp1 (localproc_036b droid1))
			(localproc_04fc temp1)
		)
		(if (= temp1 (localproc_036b droid2))
			(localproc_04fc temp1)
		)
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(if formatting
			(if (< (Abs (- gameTime theSpeed)) speed)
				0
			else
				(= theSpeed gameTime)
				(if (and local12 (== (curRoom curPic?) 699))
					(= local12 0)
				)
				(DisplayCountdown)
				(if (not (-- formatting)) (EgoDead iconDEAD deathFORMATTED))
			)
		)
		(cond 
			((curRoom script?))
			(
			(and (IsObjectOnControl ego 8) (!= curRoomNum 150)) (curRoom setScript: rgnExitScript 0 exitDir))
		)
	)
	
	(method (newRoom newRoomNumber &tmp temp0)
		(if local11 (= local11 0))
		(if (droid1 mover?) (droid1 setMotion: 0))
		(if (droid2 mover?) (droid2 setMotion: 0))
		(if
			(and
				(not
					(= keep
						(OneOf newRoomNumber
							150 500 505 510 514 515 520 525 541 544 545
						)
					)
				)
				(!= newRoomNumber 556)
			)
			(music fade:)
		)
		(= initialized 0)
		(if modelessDialog (modelessDialog dispose:))
		(globalSound fade:)
	)
	
	(method (makePolygon &tmp newPolygon)
		(= newPolygon (Polygon new:))
		(newPolygon init: &rest type: 2)
		(curRoom addObstacle: newPolygon)
	)
)

(class SecurityDroid of Sq4Actor
	(properties
		room 0
		lastRoom 0
		level 0
		path 0
		attacks 0
		body 0
	)
	
	(method (init)
		(= illegalBits 0)
		(= signal (| ignrAct ignrHrz fixedLoop))
		(self setCycle: Forward)
		(if (IsObject path) (self setMotion: path))
		(super init: &rest)
		(if body (body init:))
		(= attacks 0)
		(self doit:)
		(if body (body doit:))
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(cond 
			(script)
			((not mover) (if (!= curRoomNum 545) (self setMotion: path)))
			(else
				(switch level
					(1 (self setPri: 2))
					(2 (self setPri: 3))
					(else  (self setPri: -1))
				)
				(if
					(and
						(OneOf room curRoomNum (brain lastRoom?))
						(== level (brain level?))
					)
					(= temp0 (self distanceTo: ego))
					(cond 
						((== attacks 3) (self attack: TRUE))
						((< temp0 50) (self attack: TRUE))
						((< temp0 100) (self attack: 0))
					)
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(narrator modNum: BRAIN say: 5)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe)
		(if (super onMe: &rest) else (body onMe: &rest))
	)
	
	(method (attack param1)
		(cond 
			(
			(and (!= room (curRoom curPic?)) (!= curRoomNum 545)))
			((not (InRect 0 0 320 190 (ego x?) (ego y?))))
			((== curRoomNum 545)
				(cond 
					((not mover))
					((mover isKindOf: RegionPath) (self setMotion: PolyPath (mover x?) (mover y?)))
				)
			)
			(else (++ attacks) (self setScript: shootEgo 0 param1))
		)
	)
)

(instance droid1 of SecurityDroid
	(properties
		z 44
		view 506
		cycleSpeed 12
	)
	
	(method (init)
		(= path droidPath1)
		(= body droid1Body)
		(super init: &rest)
	)
	
	(method (doit)
		(super doit:)
		(switch room
			(525
				(if (and (self inRect: 0 0 64 40) (== level 3))
					(self setPri: 4)
				)
			)
			(515
				(if (and (< x 120) (== level 2)) (self setPri: 5))
			)
		)
	)
)

(instance droid2 of SecurityDroid
	(properties
		z 44
		view 506
		cycleSpeed 12
	)
	
	(method (init)
		(= path droidPath2)
		(= body droid2Body)
		(super init: &rest)
	)
	
	(method (doit)
		(super doit:)
		(switch room
			(525
				(if (and (self inRect: 0 0 64 40) (== level 3))
					(self setPri: 4)
				)
			)
			(515
				(if (and (< x 120) (== level 2)) (self setPri: 5))
			)
		)
	)
)

(instance droid1Body of Sq4Prop
	(properties
		view 506
		loop 1
	)
	
	(method (doit)
		(super doit:)
		(if (droid1 loop?)
		else
			(self
				x: (droid1 x?)
				y: (droid1 y?)
				z: (- (droid1 z?) 44)
				setPri: (droid1 priority?)
				signal: (| (droid1 signal?) ignrAct)
			)
		)
	)
)

(instance droid2Body of Sq4Prop
	(properties
		view 506
		loop 1
	)
	
	(method (doit)
		(super doit:)
		(if (droid2 loop?)
		else
			(self
				x: (droid2 x?)
				y: (droid2 y?)
				z: (- (droid2 z?) 44)
				setPri: (droid2 priority?)
				signal: (| (droid2 signal?) $4000)
			)
		)
	)
)

(instance droidPath1 of RegionPath
	(properties
		theRegion BRAIN
	)
	
	(method (next)
		(if (and (OneOf value 71 205) (!= curRoomNum 515))
			(= value (+ value 2))
			(client
				posn: [droidPoly (++ value)] [droidPoly (++ value)]
			)
		)
		(super next:)
	)
	
	(method (nextRoom &tmp temp0)
		(client lastRoom: (client room?))
		(super nextRoom: &rest)
		(if (== (client lastRoom?) curRoomNum)
			(globalSound fade:)
		)
		(if (= temp0 (localproc_036b client))
			(localproc_04fc temp0)
		)
	)
	
	(method (at param1)
		(client room: currentRoom level: (localproc_017a client))
		(return [droidPoly param1])
	)
)

(instance droidPath2 of RegionPath
	(properties
		value 77
		theRegion BRAIN
	)
	
	(method (next)
		(if (and (OneOf value 71 205) (!= curRoomNum 515))
			(= value (+ value 2))
			(client
				posn: [droidPoly (++ value)] [droidPoly (++ value)]
			)
		)
		(super next:)
	)
	
	(method (nextRoom &tmp temp0)
		(client lastRoom: (client room?))
		(super nextRoom: &rest)
		(if (== (client lastRoom?) curRoomNum)
			(globalSound fade:)
		)
		(if (= temp0 (localproc_036b client))
			(localproc_04fc temp0)
		)
	)
	
	(method (at param1)
		(client room: currentRoom level: (localproc_017a client))
		(return [droidPoly param1])
	)
)

(instance blast of Sq4Prop
	(properties)
)

(instance timerSound of Sound
	(properties
		number 879
	)
)

(instance laserSound of Sound
	(properties
		number 105
	)
)

(instance alertSound of Sound
	(properties
		number 101
	)
)

(instance shootEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(laserSound play:)
				(client setMotion: 0)
				(if register
					(= egoX (ego x?))
					(= local3 (- (ego y?) 15))
				else
					(= egoX (+ (ego x?) (- (Random 1 20) 10)))
					(= local3 (+ (ego y?) (- (Random 1 20) 10)))
				)
				(= local0 (Min 320 (Max 0 (client x?))))
				(= local1 (Min 190 (Max 0 (- (client y?) 44))))
				(= local4 (- (Min local1 local3) 1))
				(= local6 (+ (Max local1 local3) 1))
				(= local5 (- (Min local0 egoX) 1))
				(= local7 (+ (Max local0 egoX) 1))
				(= local8
					(Graph GSaveBits local4 local5 local6 local7 1)
				)
				(Graph
					GDrawLine
					local1
					local0
					local3
					egoX
					myTextColor13
					-1
					-1
				)
				(Graph GReAnimate local4 local5 local6 local7 1)
				(= cycles 1)
			)
			(1
				(Graph GRestoreBits local8)
				(Graph GReAnimate local4 local5 local6 local7 1)
				(client setMotion: PolyPath (ego x?) (ego y?))
				(if register
					(ego
						view: 511
						setLoop: (if (< (client x?) (ego x?)) 0 else 1)
						setCel: 0
						setMotion: 0
						setCycle: EndLoop self
					)
				else
					(blast
						view: 28
						x: egoX
						y: local3
						signal: (| ignrAct ignrHrz)
						init:
						setCycle: EndLoop self
					)
				)
			)
			(2
				(if register (EgoDead iconLASER deathDROIDBLAST) else (blast dispose:))
				(self dispose:)
			)
		)
	)
)

(instance terminal1 of Sq4Feature
	(properties
		sightAngle 90
	)
	
	(method (doVerb theVerb param2)
		(if (!= (brain level?) 1)
			(narrator modNum: BRAIN say: 6)
		else
			(switch theVerb
				(V_LOOK
					(curRoom setScript: pocketPalScript self 0)
				)
				(V_LAPTOP
					(curRoom setScript: pocketPalScript 0 self)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance terminal2 of Sq4Feature
	(properties
		sightAngle 90
	)
	
	(method (doVerb theVerb param2)
		(if (!= (brain level?) 2)
			(narrator modNum: BRAIN say: 6)
		else
			(switch theVerb
				(V_LOOK
					(curRoom setScript: pocketPalScript self 0)
				)
				(V_LAPTOP
					(curRoom setScript: pocketPalScript 0 self)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance terminal3 of Sq4Feature
	(properties
		sightAngle 90
	)
	
	(method (doVerb theVerb param2)
		(if (!= (brain level?) 3)
			(narrator modNum: BRAIN say: 6)
		else
			(switch theVerb
				(V_LOOK
					(curRoom setScript: pocketPalScript self 0)
				)
				(V_LAPTOP
					(curRoom setScript: pocketPalScript 0 self)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance pocketPalPanel of Sq4View
	(properties
		x 35
		y 46
		view 501
		priority 14
		signal fixPriOn
	)
	
	(method (doVerb theVerb)
		(pocketPalPlug doVerb: theVerb &rest)
	)
)

(instance pocketPalPlug of Sq4Prop
	(properties
		x 36
		y 41
		view 501
		loop 1
		priority 15
		signal fixPriOn
	)
	
	(method (doit)
		(super doit:)
		(if (> (ego distanceTo: theRegister) 30)
			(self dispose:)
		)
	)
	
	(method (dispose)
		(pocketPalPanel dispose:)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(narrator modNum: BRAIN say: 7)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(self dispose:)
	)
)

(instance pocketPalScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if register
					(= theRegister register)
				else
					(= theRegister caller)
					(= caller 0)
				)
				(HandsOff)
				(if (> (ego distanceTo: theRegister) 30)
					(ego
						setMotion: PolyPath (theRegister x?) (theRegister y?) self
					)
				else
					(= cycles 1)
				)
			)
			(1
				(if register
					(cond 
						((not (== ((inventory at: iPlug) owner?) 1)) (narrator modNum: BRAIN say: 8))
						((not (== ((inventory at: iBattery) owner?) 1)) (narrator modNum: BRAIN say: 9))
						((!= ((inventory at: iPlug) state?) laptopPlug) (narrator modNum: BRAIN say: 10))
						(else (brain lastRoom: curRoomNum) (curRoom newRoom: 545))
					)
				else
					(pocketPalPanel init:)
					(pocketPalPlug cel: laptopPlug init:)
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance rgnExitScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setHeading: register self)
			)
			(1
				(ego setMotion: MoveFwd 50 self)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)
