;;; Sierra Script 1.0 - (do not remove this comment)
(script# 801)
(include game.sh) (include "801.shm")
(use Main)
(use Talker)
(use Feature)
(use LoadMany)
(use Reverse)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm801 0
	alien1Tkr 1
	alien2Tkr 2
)

(local
	local0
	local1
	local2 =  1300
	local3
	local4
	local5
	local6 =  1
	local7
	local8
	local9
	local10
	local11 =  1300
	[local12 4]
	local16 =  -1
	local17
	local18
	local19
	warned
	local21
	oxygenState
	[local23 3]
	local26
	asteroidIndex
	[str 50]
	[asteroidNoun 9] = [4 5 6 7 8 9 10 11 12]
)

(procedure (DontMove)
	(theGame handsOn:)
	(theIconBar disable: ICON_WALK ICON_TALK ICON_ORDER ICON_ITEM ICON_INVENTORY)
)

(procedure (localproc_0297)
	(theMusic2 fade: 0 10 5 1)
	(= local26 0)
	(if (or local9 local10)
		(= local9 0)
		(= local10 0)
	else
		(return)
	)
)

(procedure (localproc_02be param1 &tmp i temp1 temp2 temp3 temp4 temp5 temp6 temp7)
	(if (< (= local2 (+ local2 (* param1 2))) 0)
		(= local2 3599)
	)
	(if (> local2 3599) (= local2 0))
	(= i 0)
	(while (< i (cast size?))
		(if
		((= temp1 (cast at: i)) isKindOf: FloatObj)
			(if (not (if (== temp1 cliffy) (Btst fGotCliffy)))
				(= temp5 local4)
				(= temp6 local5)
				(= temp3 (- (temp1 d3x?) temp5))
				(= temp4 (- (temp1 d3y?) temp6))
				(if
					(<
						(= temp2 (- 180 (GetAngle temp5 temp6 temp3 temp4)))
						0
					)
					(= temp2 (+ temp2 360))
				)
				(if (> (= temp2 (- 450 temp2)) 359)
					(= temp2 (- temp2 360))
				)
				(if (< (= temp2 (- temp2 (/ local2 10))) 0)
					(= temp2 (+ 360 temp2))
				)
				(if (> temp2 359) (= temp2 (- temp2 360)))
				(= temp7 (GetDistance temp5 temp6 temp3 temp4))
				(temp1
					startUpd:
					x: (+ 160 (* (SinMult temp2 temp7) 4))
				)
				(switch temp1
					(cliffy
						(if (Btst fGotCliffy)
							(cliffyBlip x: 158 y: 120)
						else
							(cliffyBlip
								x: (+ 158 (/ (SinMult temp2 temp7) 20))
								y: (- 120 (/ (CosMult temp2 temp7) 20))
							)
							(if (not (InRect 136 100 180 130 cliffyBlip))
								(cliffyBlip hide:)
							else
								(cliffyBlip show:)
							)
						)
					)
					(yourShip
						(shipBlip
							x: (+ 158 (/ (SinMult temp2 temp7) 20))
							y: (- 120 (/ (CosMult temp2 temp7) 20))
						)
						(if (not (InRect 136 100 180 130 shipBlip))
							(shipBlip hide:)
						else
							(shipBlip show:)
						)
					)
				)
				(if (and (< 90 temp2) (< temp2 270))
					(if (temp1 isNotHidden:)
						(temp1 hide:)
						(if (and (== temp1 cliffy) local21)
							(= local21 0)
							(target dispose:)
						)
					)
				else
					(if (not (temp1 isNotHidden:)) (temp1 show:))
					(switch temp1
						(cliffy
							(if
								(and
									(< (GetDistance temp5 temp6 temp3 temp4) 60)
									(<= 200 (cliffy x?))
									(<= (cliffy x?) 210)
								)
								(= local18 1)
								(if (and (not (Btst fGotCliffy)) (not local21))
									(= local21 1)
									(target init:)
								)
							else
								(= local18 0)
								(if local21 (= local21 0) (target dispose:))
							)
						)
						(yourShip
							(if (< (GetDistance temp5 temp6 temp3 temp4) 60)
								(= local19 1)
							else
								(= local19 0)
								(= warned 0)
							)
						)
					)
				)
				(if
					(<
						(= temp2 (- 220 (GetDistance temp5 temp6 temp3 temp4)))
						1
					)
					(= temp2 1)
				)
				(if (> temp2 200) (= temp2 200))
				(if (and (== temp1 cliffy) (> temp2 128))
					(= temp2 128)
				)
				(temp1 scaleX: temp2 scaleY: temp2 setPri: (/ temp2 44))
			)
		)
		(++ i)
	)
	(if (updateThrust client?)
		(updateThrust cue:)
	)
)

(procedure (localproc_05ef param1 &tmp [temp0 3])
	(= local4
		(+ local4 (CosMult (/ local2 10) (/ param1 4)))
	)
	(= local5
		(+ local5 (SinMult (/ local2 10) (/ param1 4)))
	)
	(if
		(or
			(!= local16 (/ local4 300))
			(!= local17 (/ local5 300))
		)
		(= local16 (/ local4 300))
		(= local17 (/ local5 300))
		(ast0 init: 0)
		(ast1 init: 1)
		(ast2 init: 2)
		(ast3 init: 3)
		(if (>= (theGame _detailLevel?) 2)
			(ast4 init: 4)
			(ast5 init: 5)
			(ast6 init: 6)
		)
		(if (>= (theGame _detailLevel?) 3)
			(ast7 init: 7)
			(ast8 init: 8)
		)
	)
)

(procedure (ChangeThrust)
	(if (and (!= local8 0) (not local26))
		(= local26 1)
		(theMusic2 number: 156 loop: -1 vol: 64 play:)
	)
	(switch local8
		(1
			(if (> local10 -6) (= local10 (- local10 1)))
			(if local10 (= local11 (- local11 1)))
		)
		(2
			(if (< local10 6) (= local10 (+ local10 1)))
			(if local10 (= local11 (- local11 1)))
		)
		(3
			(if (< local9 10) (= local9 (+ local9 1)))
			(if 10 (= local11 (- local11 2)))
		)
		(4
			(if (> local9 -10) (= local9 (- local9 1)))
			(if 10 (= local11 (- local11 2)))
		)
		(0 (localproc_0297))
	)
	(fuel setCel: (- (fuel lastCel:) (/ local11 100)))
	(cond 
		(
			(and
				(== (fuel cel?) (fuel lastCel:))
				(not (curRoom script?))
			)
			(= local10 0)
			(= local9 0)
			(curRoom setScript: outOfFuel)
		)
		(
		(and local19 (not (curRoom script?)) (not warned))
			(if (not (Btst fGotCliffy))
				(curRoom setScript: warning)
			else
				(curRoom setScript: returnToEureka)
			)
		)
		((not (curRoom script?))
			(localproc_05ef local9)
			(localproc_02be local10)
		)
	)
)

(class FloatObj of Prop
	(properties
		signal (| ignrAct ignrHrz skipCheck fixedLoop fixPriOn)
		scaleSignal scalable
		d3x 0
		d3y 0
	)
)

(class Asteroid of FloatObj
	(properties
		y 70
		detailLevel 3
		oldD3x 0
		oldD3y 0
	)
	
	(method (init param1)
		(= d3y (+ (* local17 200) oldD3x))
		(= d3x (+ (* local16 200) oldD3y))
		(Random (/ (+ d3x (* 2 d3y)) 10))
		(if (not (cast contains: self))
			(self setCycle: (if (Random 0 1) Forward else Reverse))
			(super init: &rest)
		)
		(if (>= (theGame _detailLevel?) detailLevel)
			(self startUpd:)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager
					say: [asteroidNoun (= asteroidIndex (mod (++ asteroidIndex) 9))] 1 0 0
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance updateThrust of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0 (ChangeThrust))
			(1 (= cycles 2))
			(2
				(hand1 stopUpd:)
				(lever1 stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance handCursor of Cursor
	(properties
		view 3270
		cel 6
	)
	
	(method (init)
		(cond 
			((and (arm cel?) (InRect 203 124 226 149 mouseX mouseY))
				(= cel 5)
			)
			((hand2 onMe: mouseX mouseY)
				(= cel (if (arm cel?) 3 else 1))
			)
			((InRect 62 134 104 160 mouseX mouseY)
				(= cel 4)
			)
			((InRect 29 134 61 160 mouseX mouseY)
				(= cel 0)
			)
			((InRect 105 134 139 160 mouseX mouseY)
				(= cel 2)
			)
			((InRect 62 104 104 133 mouseX mouseY)
				(= cel 1)
			)
			((InRect 62 161 104 189 mouseX mouseY)
				(= cel 3)
			)
			(else
				(= cel 6)
			)
		)
		(super init: &rest)
	)
)

(instance rm801 of Room
	(properties
		picture 48
	)
	
	(method (init)
		(super init: &rest)
		(LoadMany RES_VIEW 273 272 269 270 271 570 3270 3271 3272 3273)
		(theMusic1 number: 37 loop: -1 play:)
		(if (Message MsgSize 801 N_TARGET_IN_RANGE NULL NULL 1)
			(Message MsgGet 801 N_TARGET_IN_RANGE NULL NULL 1 @str)
		else
			(Format @str {%s} {Need Message})
		)
		(if (== prevRoomNum 100)
			(ego get: iOxygenTank)
		)
		((theIconBar at: ICON_DO) cursor: handCursor)
		(radar init: setOnMeCheck: ftrControl cCYAN)
		(headsUp init: setOnMeCheck: ftrControl cMAGENTA)
		(directions init: setOnMeCheck: ftrControl cBLUE)
		(fuelF init: setOnMeCheck: ftrControl cRED)
		(oxygenF init: setOnMeCheck: ftrControl cGREEN)
		(= oxygenState
			(cond 
				((== global137 2) 1)
				((not (ego has: iOxygenTank)) 2)
				(else 0)
			)
		)
		(arm init: stopUpd:)
		(lever1 init: stopUpd:)
		(lever2 init: stopUpd:)
		(hand1 init: stopUpd:)
		(hand2 init: stopUpd:)
		(lthrust init:)
		(rthrust init:)
		(fthrust init:)
		(bthrust init:)
		(if (ego has: iOxygenTank)
			(ego put: iOxygenTank)
		)
		(oxygen init: setScript: losingAir)
		(fuel init:)
		(clawButton init: setOnMeCheck: ftrDefault)
		(cliffy init: setScript: breathing)
		(yourShip init: stopUpd:)
		(cliffyBlip init: setCycle: Forward)
		(shipBlip init: setCycle: Forward)
		(localproc_05ef 0)
		(localproc_02be 0)
		(directionHandler addToFront: lever1)
		(self setScript: startItAll)
	)
	
	(method (doit)
		(if (== (theIconBar curIcon?) (theIconBar at: ICON_DO))
			(handCursor init:)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(PalVary PALVARYKILL)
		(Palette PALLoad 999 2)
		(directionHandler delete: lever1)
		((theIconBar at: ICON_DO) cursor: 982)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(cond 
					(local7
						(local7 doVerb: V_DO)
					)
					((InRect 62 134 104 160 mouseX mouseY)
						(lever1 handleEvent: dirStop)
					)
					((InRect 29 134 61 160 mouseX mouseY)
						(lever1 handleEvent: dirW)
					)
					((InRect 105 134 139 160 mouseX mouseY)
						(lever1 handleEvent: dirE)
					)
					((InRect 62 104 104 133 mouseX mouseY)
						(lever1 handleEvent: dirN)
					)
					((InRect 62 161 104 189 mouseX mouseY)
						(lever1 handleEvent: dirS)
					)
					(else (super doVerb: theVerb &rest))
				)
			)
		)
	)
)

(instance ast0 of Asteroid
	(properties
		y 52
		noun N_AST1
		d3x 100
		d3y 100
		oldD3x 100
		oldD3y 100
	)
)

(instance ast1 of Asteroid
	(properties
		y 59
		noun N_AST2
		loop 2
		d3x 100
		d3y -100
		oldD3x 100
		oldD3y -100
	)
)

(instance ast2 of Asteroid
	(properties
		y 38
		noun N_AST3
		loop 6
		d3y 300
		oldD3y 300
	)
)

(instance ast3 of Asteroid
	(properties
		y 43
		noun N_AST4
		d3x -100
		d3y -100
		oldD3x -100
		oldD3y -100
	)
)

(instance ast4 of Asteroid
	(properties
		y 34
		noun N_AST5
		loop 2
		d3x -150
		d3y 75
		oldD3x -150
		oldD3y 75
	)
)

(instance ast5 of Asteroid
	(properties
		y 50
		noun N_AST6
		loop 6
		d3x 300
		d3y 200
		oldD3x 300
		oldD3y 200
	)
)

(instance ast6 of Asteroid
	(properties
		y 36
		noun N_AST7
		d3x 200
		oldD3x 200
	)
)

(instance ast7 of Asteroid
	(properties
		y 22
		noun N_AST8
		loop 2
		d3y -200
		oldD3y -200
	)
)

(instance ast8 of Asteroid
	(properties
		y 28
		noun N_AST9
		loop 6
		d3x -200
		oldD3x -200
	)
)

(instance yourShip of FloatObj
	(properties
		y 60
		noun N_SHIP
		view 272
		loop 5
		d3y -200
	)
)

(instance cliffy of FloatObj
	(properties
		y 90
		noun N_CLIFFY
		view 272
		loop 1
		d3x 200
	)
)

(instance arm of Prop
	(properties
		x 319
		y 27
		noun N_ARM
		view 269
		priority 6
		signal (| ignrAct skipCheck fixPriOn)
		cycleSpeed 12
	)
)

(instance claw of Prop
	(properties
		x 216
		y 81
		noun N_CLAW
		view 269
		loop 1
		priority 6
		signal (| ignrAct skipCheck fixPriOn)
		cycleSpeed 12
	)
)

(instance lever1 of Prop
	(properties
		x 54
		y 135
		noun N_LEVER1
		view 270
		cel 1
		signal ignrAct
	)
	
	(method (doit &tmp [temp0 3])
		(if (!= local8 0) (ChangeThrust))
		(super doit: &rest)
	)
	
	(method (handleEvent event &tmp evt temp1)
		(return
			(cond 
				(
					(and
						(not script)
						(user canControl:)
						(== (theIconBar curIcon?) (theIconBar at: ICON_DO))
						(InRect 29 104 139 189 mouseX mouseY)
						(not (IsObject event))
					)
					(= evt event)
					(self startUpd:)
					(hand1 startUpd:)
					(switch evt
						(dirW
							(self x: 50 y: 135)
							(= temp1 1)
						)
						(dirE
							(self x: 59 y: 135)
							(= temp1 2)
						)
						(dirN
							(self x: 54 y: 130)
							(= temp1 3)
						)
						(dirS
							(self x: 54 y: 140)
							(= temp1 4)
						)
						(dirStop
							(self x: 54 y: 135)
							(= temp1 0)
						)
					)
					(if (!= temp1 local8)
						(if (!= local8 0)
							(self x: 54 y: 135)
							(= local8 0)
						else
							(= local8 temp1)
						)
					)
					(self forceUpd: setScript: updateThrust)
					(return TRUE)
				)
				((IsObject event)
					(super handleEvent: event &rest)
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(curRoom doVerb: V_DO)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance lever2 of View
	(properties
		x 270
		y 135
		noun N_LEVER2
		view 270
		loop 1
		cel 1
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (Btst fGotCliffy)
					(messager say: noun V_DO C_GOT_CLIFFY 0)
				else
					(curRoom setScript: operateArm)
				)
			)
		)
	)
)

(instance hand1 of View
	(properties
		y 125
		noun N_HAND1
		view 270
		priority 15
		signal (| ignrAct fixPriOn)
	)
	
	(method (doit)
		(self x: (- (lever1 x?) 54) y: (- (lever1 y?) 10))
		(super doit: &rest)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(curRoom doVerb: V_DO)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance hand2 of View
	(properties
		x 322
		y 124
		noun N_HAND2
		view 270
		loop 1
		priority 15
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(lever2 doVerb: theVerb &rest)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance lthrust of Prop
	(properties
		x 144
		y 169
		noun N_LTHRUST
		view 271
		loop 8
		signal ignrAct
	)
	
	(method (doit)
		(cond 
			((== local8 1)
				(if (and (!= cel (self lastCel:)) (not cycler))
					(self setCycle: EndLoop)
				)
			)
			((and cel (not cycler)) (self setCycle: BegLoop))
		)
		(super doit: &rest)
	)
)

(instance rthrust of Prop
	(properties
		x 176
		y 169
		noun N_RTHRUST
		view 271
		loop 9
		signal ignrAct
	)
	
	(method (doit)
		(cond 
			((== local8 2)
				(if (and (!= cel (self lastCel:)) (not cycler))
					(self setCycle: EndLoop)
				)
			)
			((and cel (not cycler))
				(self setCycle: BegLoop)
			)
		)
		(super doit: &rest)
	)
)

(instance fthrust of Prop
	(properties
		x 156
		y 159
		noun N_FTHRUST
		view 271
		loop 10
		signal ignrAct
	)
	
	(method (doit)
		(cond 
			((== local8 3)
				(if (and (!= cel (self lastCel:)) (not cycler))
					(self setCycle: EndLoop)
				)
			)
			((and cel (not cycler)) (self setCycle: BegLoop))
		)
		(super doit: &rest)
	)
)

(instance bthrust of Prop
	(properties
		x 155
		y 175
		noun N_BTHRUST
		view 271
		loop 11
		signal ignrAct
	)
	
	(method (doit)
		(cond 
			((== local8 4)
				(if (and (!= cel (self lastCel:)) (not cycler))
					(self setCycle: EndLoop)
				)
			)
			((and cel (not cycler)) (self setCycle: BegLoop))
		)
		(super doit: &rest)
	)
)

(instance oxygen of Prop
	(properties
		x 125
		y 112
		noun N_OXYGEN
		view 271
		loop 1
		priority 15
		signal (| ignrAct fixPriOn)
	)
	
	(method (init)
		(super init: &rest)
		(self
			setCel: (if oxygenState (- (self lastCel:) 1) else 0)
		)
	)
)

(instance fuel of Prop
	(properties
		x 186
		y 112
		noun N_FUEL
		view 271
		priority 15
		signal (| ignrAct fixPriOn)
	)
)

(instance cliffyBlip of Prop
	(properties
		noun N_CLIFFY_BLIP
		view 272
		loop 3
		priority 7
		signal (| ignrAct fixPriOn)
	)
)

(instance shipBlip of Prop
	(properties
		noun 19
		view 272
		loop 4
		priority 7
		signal (| ignrAct fixPriOn)
	)
)

(instance target of Prop
	(properties
		view 272
		loop 7
		priority 14
		signal (| ignrAct fixPriOn)
		detailLevel 3
	)
	
	(method (init)
		(super init: &rest)
		(self setCycle: Forward)
		(Display @str
			p_font userFont
			p_at 65 25
			p_color colRed
		)
	)
	
	(method (doit)
		(self x: (- (cliffy x?) 20) y: (- (cliffy y?) 34))
		(if local21
			(Display @str
				p_font userFont
				p_at 65 25
				p_color (if (= local1 (not local1)) colYellow else colRed)
			)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(Display @str
			p_font userFont
			p_at 65 25
			p_color colBlack
		)
		(super dispose: &rest)
	)
)

(instance radar of Feature
	(properties
		y 10
		noun N_RADAR
		onMeCheck cCYAN
	)
)

(instance headsUp of Feature
	(properties
		y 5
		noun N_HEADSUP
		onMeCheck cMAGENTA
	)
)

(instance directions of Feature
	(properties
		y 200
		noun N_DIRECTIONS
		onMeCheck cBLUE
	)
)

(instance fuelF of Feature
	(properties
		y 100
		noun 22
		onMeCheck cRED
	)
)

(instance oxygenF of Feature
	(properties
		y 100
		noun 26
		onMeCheck cGREEN
	)
)

(instance clawButton of Feature
	(properties
		y 200
		noun N_CLAW_BUTTON
		nsTop 124
		nsLeft 203
		nsBottom 149
		nsRight 226
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(cond 
					((Btst fGotCliffy)
						(messager say: noun V_DO C_GOT_CLIFFY 0)
					)
					((arm cel?)
						(arm setScript: operateClaw)
					)
					(else
						(arm setScript: operateArm)
					)
				)
			)
		)
	)
)

(instance operateArm of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(lever2 startUpd:)
				(hand2 startUpd:)
				(= cycles 3)
			)
			(1
				(if (== (lever2 y?) 135)
					(lever2 y: 130)
					(hand2 y: 119)
				else
					(hand2 y: 124)
					(lever2 y: 135)
				)
				(= cycles 2)
			)
			(2
				(theMusic2 number: 106 loop: 1 play:)
				(if (== (lever2 y?) 130)
					(arm setCycle: CycleTo 6 1 self)
					(lever2 y: 130)
					(hand2 y: 119)
				else
					(if (claw cel?)
						(claw setCycle: BegLoop self)
					else
						(= cycles 2)
					)
					(hand2 y: 124)
					(lever2 y: 135)
				)
			)
			(3
				(if (== (lever2 y?) 135)
					(claw dispose:)
					(arm setCel: 6 setCycle: BegLoop self)
				else
					(= cycles 1)
				)
			)
			(4
				(lever2 stopUpd:)
				(hand2 stopUpd:)
				(if (== (lever2 y?) 130)
					(claw init: stopUpd:)
					(arm setCel: 7)
				)
				(arm stopUpd:)
				(DontMove)
				(self dispose:)
			)
		)
	)
)

(instance operateClaw of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(claw startUpd:)
				(= cycles 2)
			)
			(1
				(theMusic2 number: 108 loop: 1 play:)
				(if (claw cel?)
					(if
						(and
							local18
							(<= 200 (cliffy x?))
							(<= (cliffy x?) 210)
						)
						(Bset fGotCliffy)
						(= local21 0)
						(target dispose:)
						(claw setCel: (- (claw lastCel:) 1))
						(= cycles 2)
					else
						(claw setCycle: BegLoop self)
					)
				else
					(claw setCycle: EndLoop self)
				)
			)
			(2
				(if (Btst fGotCliffy)
					(self setScript: getCliffy self)
				else
					(= cycles 1)
				)
			)
			(3
				(claw stopUpd:)
				(DontMove)
				(self dispose:)
			)
		)
	)
)

(instance getCliffy of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(claw setCel: (- (claw lastCel:) 1))
				(= cycles 2)
			)
			(1
				(SolvePuzzle f801GetCliffy 100)
				(UnLoad RES_VIEW 273)
				(cliffy
					view: 272
					loop: 1
					cel: 0
					setPri: (- (claw priority?) 1)
					stopUpd:
				)
				(messager say: N_LEVER2 NULL C_GOT_CLIFFY 0 self)
			)
			(2
				(cliffy addToPic:)
				(claw addToPic:)
				(arm addToPic:)
				(hand2 addToPic:)
				(lever2 addToPic:)
				(self dispose:)
			)
		)
	)
)

(instance losingAir of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 20))
			(1
				(if (!= (oxygen cel?) (oxygen lastCel:))
					(oxygen setCel: (+ (oxygen cel?) 1))
					(if
					(and (> (oxygen cel?) 9) (!= (theMusic3 number?) 282))
						(theMusic3 number: 282 loop: -1 play:)
					)
					(= state -1)
				)
				(if (not (breathing state?)) (breathing seconds: 1))
				(= cycles 1)
			)
			(2
				(theGame handsOff:)
				(theMusic1 fade: 0 10 5 1)
				(theMusic2 stop:)
				(switch oxygenState
					(0
						(messager say: N_OXYGEN NULL C_SCREWED_AROUND 0 self)
					)
					(1
						(messager say: N_OXYGEN NULL C_NO_TANK 0 self)
					)
					(2
						(messager say: N_OXYGEN NULL C_NO_AIR 0 self)
					)
				)
			)
			(3
				((theIconBar at: ICON_DO) cursor: 982)
				(PalVary PALVARYKILL)
				(aliens register: (if (== oxygenState 2) deathOUTOFAIR else deathNOAIRTANK))
				(curRoom setScript: aliens)
				(self dispose:)
			)
		)
	)
)

(instance breathing of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (* (- 14 (oxygen cel?)) 2))
			)
			(1
				(theMusic4 number: 550 loop: 1 play:)
				(= state (- state 2))
				(= seconds 1)
			)
		)
	)
)

(instance outOfFuel of Script
	
	(method (changeState newState &tmp temp0 i)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((theIconBar at: ICON_DO) cursor: 982)
				(theMusic1 fade: 0 10 5 1)
				(theMusic2 stop:)
				(PalVary PALVARYSTART 48 5)
				(= register 30)
				(= cycles 1)
			)
			(1
				(= i 0)
				(while (< i (cast size?))
					(if
					((= temp0 (cast at: i)) isKindOf: FloatObj)
						(if (not (if (== temp0 cliffy) (Btst fGotCliffy)))
							(temp0 startUpd: y: (- (temp0 y?) 2))
						)
					)
					(++ i)
				)
				(if (-- register) (-- state))
				(= cycles 2)
			)
			(2
				(messager say: N_FUEL NULL C_SCREWED_AROUND 0 self)
			)
			(3
				((theIconBar at: ICON_DO) cursor: 982)
				(aliens register: deathNOGAS)
				(PalVary PALVARYKILL)
				(curRoom setScript: aliens)
				(self dispose:)
			)
		)
	)
)

(instance startItAll of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 3)
			)
			(1
				(hand1 setCel: 2)
				(hand2 setCel: 2)
				(= cycles 3)
			)
			(2
				(DontMove)
				(self dispose:)
			)
		)
	)
)

(instance warning of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: N_RETURN_TO_EUREKA NULL C_NEED_CLIFFY 0 self)
				(= warned 1)
			)
			(1 (self dispose:))
		)
	)
)

(instance returnToEureka of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(SolvePuzzle f801ReturnToEureka 50)
				(messager say: N_RETURN_TO_EUREKA NULL C_CLIFFY_BACK 0 self)
				(theMusic1 fade: 0 10 5 1)
			)
			(1
				((theIconBar at: ICON_DO) cursor: 982)
				(= global137 2)
				(theMusic1 stop:)
				(theMusic2 stop:)
				(theMusic3 stop:)
				(curRoom newRoom: 250)
			)
		)
	)
)

(instance shootingStar of Actor
	(properties
		x 233
		y 42
		view 570
		priority 1
		signal (| ignrAct ignrHrz fixPriOn)
		moveSpeed 1
	)
)

(instance aliens of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theMusic2 stop:)
				(theMusic3 stop:)
				(theMusic4 stop:)
				(cast eachElementDo: #hide)
				(PalVary PALVARYKILL)
				(= cycles 3)
			)
			(1
				(cast eachElementDo: #dispose)
				(curRoom drawPic: 56 -32758)
				(= seconds 3)
			)
			(2
				(shootingStar
					init:
					setCycle: Forward
					setMotion: MoveTo 153 167 self
				)
			)
			(3 (messager say: N_ALIENS V_TALK C_DEATH 0 self))
			(4 (EgoDead register))
		)
	)
)

(instance alien1Tkr of Narrator
	(properties
		talkWidth 100
	)
	
	(method (init)
		(= systemWindow theSpeakWindow)
		(= font userFont)
		(systemWindow
			tailX: 80
			xOffset: -20
			tailY: 80
			isBottom: TRUE
		)
		(super init: &rest)
	)
	
	(method (dispose)
		(= systemWindow gSq5Win_2)
		(super dispose: &rest)
	)
)

(instance alien2Tkr of Narrator
	(properties
		talkWidth 100
	)
	
	(method (init)
		(= systemWindow theSpeakWindow)
		(= font userFont)
		(systemWindow
			tailX: 180
			xOffset: 20
			isBottom: TRUE
			tailY: 80
		)
		(super init: &rest)
	)
	
	(method (dispose)
		(= systemWindow gSq5Win_2)
		(super dispose: &rest)
	)
)

(instance theMusic3 of Sound)

(instance theMusic4 of Sound)
