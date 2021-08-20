;;; Sierra Script 1.0 - (do not remove this comment)
(script# 280)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use User)
(use System)

(public
	rm280 0
)

(local
	local0
	orderedTang
	orderedFood
	menuOption
	local4
	local5 =  30
	[local6 12] = [27 35 43 51 59 67 91 99 123 131 139 147]
	[local18 12] = [32 40 48 56 64 72 96 104 128 136 144 152]
)

(enum 1 ;menu choices
	foodMINIMONOLITH
	foodMONOLITH
	foodWITHPOLYCHEEZE
	foodFILET
	foodJUMBO
	foodBIGBELCHER
	foodFUNMEAL
	foodSPACESPUDS
	tangSMALL
	tangMEDIUM
	tangLARGE
	tangSLOPPYSLURPER
)

(instance rm280 of Room
	(properties
		picture 32
	)
	
	(method (init &tmp [temp0 50])
		(Load PICTURE 32)
		(super init:)
		(= saveDisabled TRUE)
		(User canInput: FALSE canControl: FALSE)
		(User mapKeyToDir: FALSE)
		(= global592 1)
		(theGame setCursor: normalCursor (HaveMouse))
		(Display 280 0
			p_at 10 27
			p_width 310
			p_color vBLACK
			p_font 600
		)
		(Display 280 0
			p_at 11 28
			p_width 310
			p_color vWHITE
			p_font 600
		)
		(if (== prevRoomNum 25)
			(Display 280 1
				p_at 30 175
				p_width 200
				p_color vBLACK
				p_font 600
			)
			(Display 280 1
				p_at 31 176
				p_width 200
				p_color vWHITE
				p_font 600
			)
		)
		(Display 280 2
			p_at 200 175
			p_width 200
			p_color vBLACK
			p_font 600
		)
		(Display 280 2
			p_at 201 176
			p_width 200
			p_color vWHITE
			p_font 600
		)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(switch (event type?)
			(mouseDown
				(cond 
					((== prevRoomNum 25)
						(cond 
							(
								(and
									(<= 190 (event x?))
									(<= (event x?) 275)
									(<= 170 (event y?))
									(<= (event y?) 183)
								)
								(event claimed: TRUE)
								(curRoom setScript: Quitting)
							)
							(
								(and
									(<= local4 (event x?))
									(<= (event x?) local5)
									(<= [local6 0] (event y?))
									(<= (event y?) [local18 0])
								)
								(= menuOption foodMINIMONOLITH)
								(curRoom setScript: ChooseFood)
							)
							(
								(and
									(<= local4 (event x?))
									(<= (event x?) local5)
									(<= [local6 1] (event y?))
									(<= (event y?) [local18 1])
								)
								(= menuOption foodMONOLITH)
								(curRoom setScript: ChooseFood)
							)
							(
								(and
									(<= local4 (event x?))
									(<= (event x?) local5)
									(<= [local6 2] (event y?))
									(<= (event y?) [local18 2])
								)
								(= menuOption foodWITHPOLYCHEEZE)
								(curRoom setScript: ChooseFood)
							)
							(
								(and
									(<= local4 (event x?))
									(<= (event x?) local5)
									(<= [local6 3] (event species?))
									(<= (event species?) [local18 3])
								)
								(= menuOption foodFILET)
								(curRoom setScript: ChooseFood)
							)
							(
								(and
									(<= local4 (event x?))
									(<= (event x?) local5)
									(<= [local6 4] (event y?))
									(<= (event y?) [local18 4])
								)
								(= menuOption foodJUMBO)
								(curRoom setScript: ChooseFood)
							)
							(
								(and
									(<= local4 (event x?))
									(<= (event x?) local5)
									(<= [local6 5] (event y?))
									(<= (event y?) [local18 5])
								)
								(= menuOption foodBIGBELCHER)
								(curRoom setScript: ChooseFood)
							)
							(
								(and
									(<= local4 (event x?))
									(<= (event x?) local5)
									(<= [local6 6] (event y?))
									(<= (event y?) [local18 6])
								)
								(= menuOption foodFUNMEAL)
								(curRoom setScript: ChooseFood)
							)
							(
								(and
									(<= local4 (event x?))
									(<= (event x?) local5)
									(<= [local6 7] (event y?))
									(<= (event y?) [local18 7])
								)
								(= menuOption foodSPACESPUDS)
								(curRoom setScript: ChooseFood)
							)
							(
								(and
									(<= local4 (event x?))
									(<= (event x?) local5)
									(<= [local6 8] (event y?))
									(<= (event y?) [local18 8])
								)
								(= menuOption tangSMALL)
								(curRoom setScript: ChooseFood)
							)
							(
								(and
									(<= local4 (event x?))
									(<= (event x?) local5)
									(<= [local6 9] (event y?))
									(<= (event y?) [local18 9])
								)
								(= menuOption tangMEDIUM)
								(curRoom setScript: ChooseFood)
							)
							(
								(and
									(<= local4 (event x?))
									(<= (event x?) local5)
									(<= [local6 10] (event y?))
									(<= (event y?) [local18 10])
								)
								(= menuOption tangLARGE)
								(curRoom setScript: ChooseFood)
							)
							(
								(and
									(<= local4 (event x?))
									(<= (event x?) local5)
									(<= [local6 11] (event y?))
									(<= (event y?) [local18 11])
								)
								(= menuOption tangSLOPPYSLURPER)
								(curRoom setScript: ChooseFood)
							)
						)
					)
					(
						(and
							(<= 190 (event x?))
							(<= (event x?) 275)
							(<= 170 (event y?))
							(<= (event y?) 183)
						)
						(event claimed: TRUE)
						(curRoom setScript: Quitting)
					)
				)
			)
			(keyDown
				(cond 
					((== prevRoomNum 25)
						(cond 
							(
								(or
									(== (event message?) `Q)
									(== (event message?) `q)
								)
								(curRoom setScript: Quitting)
							)
							(
								(and
									(>= (event message?) `1)
									(<= (event message?) `9)
								)
								(= menuOption (- (event message?) `0))
								(curRoom setScript: ChooseFood)
							)
							(
								(and
									(>= (event message?) `A)
									(<= (event message?) `C)
								)
								(= menuOption (- (event message?) `7))
								(curRoom setScript: ChooseFood)
							)
							(
								(and
									(>= (event message?) `a)
									(<= (event message?) `c)
								)
								(= menuOption (- (event message?) `W))
								(curRoom setScript: ChooseFood)
							)
						)
					)
					(
						(or
							(== (event message?) `Q)
							(== (event message?) `q)
						)
						(curRoom setScript: Quitting)
					)
				)
			)
		)
	)
)

(instance ChooseFood of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch menuOption
					(0)
					(foodMINIMONOLITH
						(= monolithBurgerBill (+ monolithBurgerBill 1))
						(= orderedFood TRUE)
					)
					(foodMONOLITH
						(= monolithBurgerBill (+ monolithBurgerBill 2))
						(= orderedFood TRUE)
					)
					(foodWITHPOLYCHEEZE
						(= monolithBurgerBill (+ monolithBurgerBill 3))
						(= orderedFood TRUE)
					)
					(foodFILET
						(= monolithBurgerBill (+ monolithBurgerBill 2))
						(= orderedFood TRUE)
					)
					(foodJUMBO
						(= monolithBurgerBill (+ monolithBurgerBill 5))
						(= orderedFood TRUE)
					)
					(foodBIGBELCHER
						(= monolithBurgerBill (+ monolithBurgerBill 9))
						(= orderedFood TRUE)
						(= orderedBigBelcherCombo TRUE)
					)
					(foodFUNMEAL
						(= monolithBurgerBill (+ monolithBurgerBill 7))
						(if (not (ego has: iDecoderRing)) (= mealHasDecoderRing TRUE))
						(= orderedFood TRUE)
					)
					(foodSPACESPUDS
						(= monolithBurgerBill (+ monolithBurgerBill 1))
						(= orderedFood TRUE)
					)
					(tangSMALL
						(= monolithBurgerBill (+ monolithBurgerBill 1))
						(= orderedTang TRUE)
					)
					(tangMEDIUM
						(= monolithBurgerBill (+ monolithBurgerBill 2))
						(= orderedTang TRUE)
					)
					(tangLARGE
						(= monolithBurgerBill (+ monolithBurgerBill 3))
						(= orderedTang TRUE)
					)
					(tangSLOPPYSLURPER
						(= monolithBurgerBill (+ monolithBurgerBill 4))
						(= orderedTang TRUE)
					)
				)
				(Print 280 3 #at -1 95 #width 50 #font 600 #dispose)
				(= cycles 30)
			)
			(1
				(cls)
				(curRoom setScript: 0)
			)
		)
	)
)

(instance Quitting of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (and (== prevRoomNum 25) monolithBurgerBill)
					(if (not orderedTang)
						(Print 280 4
							#icon 38 4 0
							#mode teJustCenter
							#title {Pushy Counter Clerk}
							#button { Yes_} 1
							#button { Yes_} 2
						)
					)
					(Print 280 5
						#icon 38 4 1
						#mode teJustCenter
						#title {Pushy Counter Clerk}
						#button { Yes_} 1
						#button { Yes_} 2
					)
					(Print 280 6
						#icon 38 4 2
						#mode teJustCenter
						#title {Pushy Counter Clerk}
						#button { Yes_} 1
						#button { Yes_} 2
					)
					(if (not orderedTang)
						(Print 280 7
							#icon 38 4 0
							#mode teJustCenter
							#title {While Supplies Last}
							#button { Okay_} teJustCenter
						)
					)
					(if (not orderedFood)
						(Print 280 8
							#icon 38 4 0
							#mode teJustCenter
							#title {While Supplies Last}
							#button { Okay_} 1
						)
					)
					(= global592 0)
					(= saveDisabled FALSE)
					(if (> monolithBurgerBill 9999) (= monolithBurgerBill 9999))
					(User mapKeyToDir: TRUE)
					(cls)
					(curRoom newRoom: 25)
				else
					(= global592 0)
					(= saveDisabled FALSE)
					(User mapKeyToDir: TRUE)
					(curRoom newRoom: prevRoomNum)
				)
			)
		)
	)
)
