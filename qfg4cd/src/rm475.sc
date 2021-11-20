;;; Sierra Script 1.0 - (do not remove this comment)
(script# 475)
(include sci.sh)
(use Main)
(use GloryRm)
(use Scaler)
(use Feature)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm475 0
)

(local
	local0
	newATurnCard
	local2
)
(procedure (localproc_1cae &tmp temp0)
	(= temp0 100)
	(while (>= temp0 0)
		(Palette palSET_FLAG 0 255 temp0)
		(-- temp0)
	)
)

(procedure (localproc_1cce &tmp temp0)
	(= temp0 0)
	(while (<= temp0 100)
		(Palette palSET_FLAG 0 65 temp0)
		(Palette palSET_FLAG 86 255 temp0)
		(++ temp0)
	)
)

(procedure (localproc_1cfb)
	(theIconBar enable: 2)
	(user canInput: 1)
	(theIconBar advanceCurIcon:)
)

(instance rm475 of GloryRm
	(properties)
	
	(method (init)
		(theGame handsOff:)
		(Bset 50)
		(ego x: 1000 y: 1000 init:)
		(if (not (Btst 300))
			(vDeck1 init:)
			(vDeck2 init:)
			(vDeck3 init:)
		)
		(theDeck init:)
		(CyclePalette)
		(CyclePalette_13)
		(if
			(not
				(OneOf
					curRoomNum
					551
					552
					553
					554
					555
					556
					557
					558
					559
					560
					561
					562
					563
					564
					565
					566
					567
					568
					569
					570
					571
					572
					573
					574
					575
					576
					577
					578
					579
					580
					581
					582
					583
					584
					585
					586
					587
					588
					589
					590
					591
					592
					593
					810
					720
					800
					632
				)
			)
			(= global470
				(= global365
					(= global366
						(= gABad2Health (= gABad3Health (= gABad4Health 0)))
					)
				)
			)
		)
		(= number curRoomNum)
		(= perspective picAngle)
		(if (not plane) (= plane thePlane))
		(UpdatePlane
			((curRoom plane?) back: 29 picture: -1 yourself:)
		)
		(RemapColors 2 254 50)
		(cond 
			((Btst 300) (Bset 301) (curRoom setScript: sFifthReading))
			((Btst 299) (Bset 300) (curRoom setScript: sFourthReading))
			((Btst 298) (Bset 299) (curRoom setScript: sThirdReading))
			((Btst 297) (Bset 298) (curRoom setScript: sSecondReading))
			(else (Bset 297) (curRoom setScript: sFirstReading))
		)
	)
	
	(method (doit)
		(super doit: &rest)
		(if Night (Palette palSET_FROM_RESOURCE 475 2))
	)
	
	(method (dispose)
		(Bclr 50)
		(super dispose:)
	)
)

(instance sFirstReading of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 1))
			(1 (messager say: 1 6 1 0 self))
			(2 (localproc_1cfb))
			(3
				(switch heroType
					(0 (= local0 1044))
					(1 (= local0 1001))
					(3 (= local0 1002))
					(2 (= local0 1003))
				)
				(= local2 1)
				(ego setScript: sShowCard self)
			)
			(4
				(newATurnCard setPri: 0)
				(switch heroType
					(0 (messager say: 1 6 2 0 self))
					(2 (messager say: 1 6 3 0 self))
					(1 (messager say: 1 6 4 0 self))
					(3 (messager say: 1 6 5 0 self))
				)
			)
			(5 (messager say: 1 6 6 0 self))
			(6 (localproc_1cfb))
			(7
				(= local0 1011)
				(= local2 2)
				(ego setScript: sShowCard self)
			)
			(8
				(newATurnCard setPri: 0)
				(messager say: 1 6 7 0 self)
			)
			(9 (localproc_1cfb))
			(10
				(= local0 1012)
				(= local2 2)
				(ego setScript: sShowCard self)
			)
			(11
				(newATurnCard setPri: 1)
				(messager say: 1 6 8 0 self)
			)
			(12 (localproc_1cfb))
			(13
				(= local0 1007)
				(= local2 3)
				(ego setScript: sShowCard self)
			)
			(14
				(newATurnCard setPri: 0)
				(messager say: 1 6 9 0 self)
			)
			(15 (localproc_1cfb))
			(16
				(= local0 1008)
				(= local2 3)
				(ego setScript: sShowCard self)
			)
			(17
				(newATurnCard setPri: 1)
				(messager say: 1 6 10 0 self)
			)
			(18 (localproc_1cfb))
			(19
				(= local0 1009)
				(= local2 4)
				(ego setScript: sShowCard self)
			)
			(20
				(newATurnCard setPri: 0)
				(messager say: 1 6 11 0 self)
			)
			(21 (localproc_1cfb))
			(22
				(= local0 1010)
				(= local2 4)
				(ego setScript: sShowCard self)
			)
			(23
				(newATurnCard setPri: 1)
				(messager say: 1 6 12 0 self)
			)
			(24 (localproc_1cfb))
			(25
				(= local0 1005)
				(= local2 5)
				(ego setScript: sShowCard self)
			)
			(26
				(newATurnCard setPri: 0)
				(messager say: 1 6 13 0 self)
			)
			(27 (localproc_1cfb))
			(28
				(= local0 1006)
				(= local2 5)
				(ego setScript: sShowCard self)
			)
			(29
				(newATurnCard setPri: 1)
				(messager say: 1 6 14 0 self)
			)
			(30 (localproc_1cfb))
			(31
				(= local0 1004)
				(ego setScript: sTurnTheCard self)
			)
			(32
				(messager say: 1 6 15 0 self)
			)
			(33 (curRoom newRoom: 470))
		)
	)
)

(instance sSecondReading of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 1))
			(1
				(messager say: 1 6 16 0 self)
			)
			(2 (localproc_1cfb))
			(3
				(= local0 1007)
				(= local2 1)
				(ego setScript: sShowCard self)
			)
			(4
				(newATurnCard setPri: 0)
				(messager say: 1 6 17 0 self)
			)
			(5 (localproc_1cfb))
			(6
				(= local0 1005)
				(= local2 2)
				(ego setScript: sShowCard self)
			)
			(7
				(newATurnCard setPri: 0)
				(messager say: 1 6 19 0 self)
			)
			(8 (localproc_1cfb))
			(9
				(= local0 1013)
				(= local2 2)
				(ego setScript: sShowCard self)
			)
			(10
				(newATurnCard setPri: 1)
				(messager say: 1 6 20 0 self)
			)
			(11 (localproc_1cfb))
			(12
				(switch heroType
					(0 (= local0 1044))
					(1 (= local0 1001))
					(3 (= local0 1002))
					(2 (= local0 1003))
				)
				(= local2 3)
				(ego setScript: sShowCard self)
			)
			(13
				(newATurnCard setPri: 0)
				(messager say: 1 6 22 0 self)
			)
			(14 (localproc_1cfb))
			(15
				(= local0 1014)
				(= local2 3)
				(ego setScript: sShowCard self)
			)
			(16
				(newATurnCard setPri: 1)
				(messager say: 1 6 21 0 self)
			)
			(17 (localproc_1cfb))
			(18
				(= local0 1015)
				(= local2 4)
				(ego setScript: sShowCard self)
			)
			(19
				(newATurnCard setPri: 0)
				(messager say: 1 6 25 0 self)
			)
			(20 (localproc_1cfb))
			(21
				(= local0 1016)
				(= local2 4)
				(ego setScript: sShowCard self)
			)
			(22
				(newATurnCard setPri: 1)
				(messager say: 1 6 26 0 self)
			)
			(23 (localproc_1cfb))
			(24
				(= local0 1017)
				(= local2 5)
				(ego setScript: sShowCard self)
			)
			(25
				(newATurnCard setPri: 0)
				(messager say: 1 6 27 0 self)
			)
			(26 (localproc_1cfb))
			(27
				(= local0 1018)
				(= local2 5)
				(ego setScript: sShowCard self)
			)
			(28
				(newATurnCard setPri: 1)
				(messager say: 1 6 28 0 self)
			)
			(29 (localproc_1cfb))
			(30
				(= local0 1004)
				(ego setScript: sTurnTheCard self)
			)
			(31
				(messager say: 1 6 29 0 self)
			)
			(32 (curRoom newRoom: 470))
		)
	)
)

(instance sThirdReading of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 1))
			(1
				(messager say: 1 6 30 0 self)
			)
			(2 (localproc_1cfb))
			(3
				(= local0 1009)
				(= local2 1)
				(ego setScript: sShowCard self)
			)
			(4
				(newATurnCard setPri: 0)
				(messager say: 1 6 31 0 self)
			)
			(5 (localproc_1cfb))
			(6
				(= local0 1015)
				(= local2 2)
				(ego setScript: sShowCard self)
			)
			(7
				(newATurnCard setPri: 1)
				(messager say: 1 6 32 0 self)
			)
			(8 (localproc_1cfb))
			(9
				(= local0 1020)
				(= local2 2)
				(ego setScript: sShowCard self)
			)
			(10
				(newATurnCard setPri: 0)
				(messager say: 1 6 33 0 self)
			)
			(11 (localproc_1cfb))
			(12
				(= local0 1001)
				(= local2 3)
				(ego setScript: sShowCard self)
			)
			(13
				(newATurnCard setPri: 1)
				(messager say: 1 6 34 0 self)
			)
			(14 (localproc_1cfb))
			(15
				(= local0 1018)
				(= local2 3)
				(ego setScript: sShowCard self)
			)
			(16
				(newATurnCard setPri: 0)
				(messager say: 1 6 35 0 self)
			)
			(17 (localproc_1cfb))
			(18
				(= local0 1022)
				(= local2 4)
				(ego setScript: sShowCard self)
			)
			(19
				(newATurnCard setPri: 1)
				(messager say: 1 6 36 0 self)
			)
			(20 (localproc_1cfb))
			(21
				(= local0 1023)
				(= local2 4)
				(ego setScript: sShowCard self)
			)
			(22
				(newATurnCard setPri: 2)
				(messager say: 1 6 37 0 self)
			)
			(23 (localproc_1cfb))
			(24
				(= local0 1024)
				(= local2 5)
				(ego setScript: sShowCard self)
			)
			(25
				(newATurnCard setPri: 0)
				(messager say: 1 6 38 0 self)
			)
			(26 (localproc_1cfb))
			(27
				(= local0 1025)
				(= local2 5)
				(ego setScript: sShowCard self)
			)
			(28
				(newATurnCard setPri: 1)
				(messager say: 1 6 39 0 self)
			)
			(29 (localproc_1cfb))
			(30
				(= local0 1004)
				(ego setScript: sTurnTheCard self)
			)
			(31
				(messager say: 1 6 40 0 self)
			)
			(32 (curRoom newRoom: 470))
		)
	)
)

(instance sFourthReading of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 1))
			(1
				(messager say: 1 6 41 0 self)
			)
			(2 (localproc_1cfb))
			(3
				(= local0 1009)
				(= local2 1)
				(ego setScript: sShowCard self)
			)
			(4
				(newATurnCard setPri: 0)
				(messager say: 1 6 42 0 self)
			)
			(5 (localproc_1cfb))
			(6
				(= local0 1001)
				(= local2 2)
				(ego setScript: sShowCard self)
			)
			(7
				(newATurnCard setPri: 1)
				(messager say: 1 6 43 0 self)
			)
			(8 (localproc_1cfb))
			(9
				(= local0 1026)
				(= local2 2)
				(ego setScript: sShowCard self)
			)
			(10
				(newATurnCard setPri: 2)
				(messager say: 1 6 44 0 self)
			)
			(11 (localproc_1cfb))
			(12
				(= local0 1015)
				(= local2 3)
				(ego setScript: sShowCard self)
			)
			(13
				(newATurnCard setPri: 0)
				(messager say: 1 6 45 0 self)
			)
			(14 (localproc_1cfb))
			(15
				(= local0 1050)
				(= local2 3)
				(ego setScript: sShowCard self)
			)
			(16
				(newATurnCard setPri: 1)
				(messager say: 1 6 46 0 self)
			)
			(17 (localproc_1cfb))
			(18
				(= local0 1027)
				(= local2 4)
				(ego setScript: sShowCard self)
			)
			(19
				(newATurnCard setPri: 0)
				(messager say: 1 6 47 0 self)
			)
			(20 (localproc_1cfb))
			(21
				(= local0 1028)
				(= local2 4)
				(ego setScript: sShowCard self)
			)
			(22
				(newATurnCard setPri: 1)
				(messager say: 1 6 48 0 self)
			)
			(23 (localproc_1cfb))
			(24
				(= local0 1029)
				(= local2 5)
				(ego setScript: sShowCard self)
			)
			(25
				(newATurnCard setPri: 0)
				(messager say: 1 6 49 0 self)
			)
			(26 (localproc_1cfb))
			(27
				(switch heroType
					(0 (= local0 1047))
					(1 (= local0 1013))
					(3 (= local0 1045))
					(2 (= local0 1046))
				)
				(= local2 5)
				(ego setScript: sShowCard self)
			)
			(28
				(newATurnCard setPri: 1)
				(messager say: 1 6 50 0 self)
			)
			(29 (localproc_1cfb))
			(30
				(= local0 1004)
				(ego setScript: sTurnTheCard self)
			)
			(31
				(messager say: 1 6 51 0 self)
			)
			(32 (curRoom newRoom: 470))
		)
	)
)

(instance sFifthReading of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 1))
			(1
				(messager say: 1 6 52 0 self)
			)
			(2
				(= local0 1036)
				(ego setScript: sSetTheSignificator)
			)
			(3
				(newATurnCard setPri: 0)
				(messager say: 1 6 53 0 self)
			)
			(4
				(= local0 1040)
				(ego setScript: sSetTheSignificator)
			)
			(5
				(newATurnCard setPri: 1)
				(messager say: 1 6 54 0 self)
			)
			(6
				(= local0 1041)
				(ego setScript: sSetTheSignificator)
			)
			(7
				(newATurnCard setPri: 2)
				(messager say: 1 6 55 0 self)
			)
			(8
				(messager say: 1 6 56 0 self)
			)
			(9 (ego setScript: sFadeIt))
			(10
				(messager say: 1 6 57 0 self)
			)
			(11
				(= local0 1039)
				(ego setScript: sSetTheSignificator)
			)
			(12
				(newATurnCard setPri: 0)
				(messager say: 1 6 58 0 self)
			)
			(13
				(= local0 1031)
				(ego setScript: sSetTheSignificator)
			)
			(14
				(newATurnCard setPri: 1)
				(messager say: 1 6 61 0 self)
			)
			(15
				(= local0 1004)
				(ego setScript: sSetTheSignificator)
			)
			(16
				(newATurnCard setPri: 2)
				(messager say: 1 6 59 0 self)
			)
			(17
				(messager say: 1 6 60 0 self)
			)
			(18 (ego setScript: sFadeIt))
			(19
				(messager say: 1 6 62 0 self)
			)
			(20
				(= local0 1035)
				(ego setScript: sSetTheSignificator)
			)
			(21
				(newATurnCard setPri: 0)
				(messager say: 1 6 63 0 self)
			)
			(22
				(= local0 1011)
				(ego setScript: sSetTheSignificator)
			)
			(23
				(newATurnCard setPri: 1)
				(messager say: 1 6 64 0 self)
			)
			(24
				(messager say: 1 6 65 0 self)
			)
			(25 (ego setScript: sFadeIt))
			(26
				(messager say: 1 6 66 0 self)
			)
			(27
				(= local0 1049)
				(ego setScript: sSetTheSignificator)
			)
			(28
				(newATurnCard setPri: 0)
				(messager say: 1 6 67 0 self)
			)
			(29
				(= local0 1031)
				(ego setScript: sSetTheSignificator)
			)
			(30
				(messager say: 1 6 69 0 self)
			)
			(31
				(= local0 1048)
				(ego setScript: sSetTheSignificator)
			)
			(32
				(newATurnCard setPri: 1)
				(messager say: 1 6 68 0 self)
			)
			(33 (ego setScript: sFadeIt))
			(34
				(messager say: 1 6 70 0 self)
			)
			(35
				(= local0 1034)
				(ego setScript: sSetTheSignificator)
			)
			(36
				(newATurnCard setPri: 0)
				(messager say: 1 6 71 0 self)
			)
			(37
				(if (> [egoStats 12] 0)
					(= local0 1032)
				else
					(= local0 1043)
				)
				(ego setScript: sSetTheSignificator)
			)
			(38
				(newATurnCard setPri: 1)
				(if (> [egoStats 12] 0)
					(messager say: 1 6 72 0 self)
				else
					(messager say: 1 6 74 0 self)
				)
			)
			(39
				(if (> [egoStats 12] 0)
					(= local0 1033)
				else
					(= local0 1012)
				)
				(ego setScript: sSetTheSignificator)
			)
			(40
				(newATurnCard setPri: 2)
				(if (> [egoStats 12] 0)
					(messager say: 1 6 73 0 self)
				else
					(messager say: 1 6 75 0 self)
				)
			)
			(41
				(messager say: 1 6 76 0 self)
			)
			(42
				(cast eachElementDo: #hide)
				(localproc_1cae)
				(= seconds 2)
			)
			(43 (ego setScript: sFadeIt))
			(44
				(messager say: 1 6 77 0 self)
			)
			(45
				(= local0 1038)
				(ego setScript: sSetTheSignificator)
			)
			(46
				(newATurnCard setPri: 0)
				(messager say: 1 6 78 0 self)
			)
			(47
				(= local0 1042)
				(ego setScript: sSetTheSignificator)
			)
			(48
				(newATurnCard setPri: 1)
				(messager say: 1 6 79 0 self)
			)
			(49
				(= local0 1004)
				(ego setScript: sSetTheSignificator)
			)
			(50
				(newATurnCard setPri: 2)
				(messager say: 1 6 80 0 self)
			)
			(51 (ego setScript: sFadeIt))
			(52
				(messager say: 1 6 81 0 self)
			)
			(53
				(= local0 1029)
				(ego setScript: sSetTheSignificator)
			)
			(54
				(newATurnCard setPri: 0)
				(messager say: 1 6 82 0 self)
			)
			(55
				(= local0 1030)
				(ego setScript: sSetTheSignificator)
			)
			(56
				(newATurnCard setPri: 1)
				(messager say: 1 6 84 0 self)
			)
			(57 (ego setScript: sFadeIt))
			(58
				(messager say: 1 6 83 0 self)
			)
			(59 (curRoom newRoom: 470))
		)
	)
)

(instance sSetTheSignificator of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(UpdatePlane
					((curRoom plane?) back: 29 picture: -1 yourself:)
				)
				((= newATurnCard (aTurnCard new:))
					view: local0
					posn: 148 189
					init:
					moveSpeed: 0
					setPri: 12
					setStep: 30 10
					setScaler: Scaler 100 100 189 0
				)
				(= seconds 2)
			)
			(1
				(newATurnCard
					moveSpeed: 0
					setStep: 30 10
					setScaler: Scaler 100 35 189 110
					setMotion: MoveTo 145 110 self
				)
			)
			(2 (= seconds 1))
			(3
				((curRoom script?) cue:)
				(sSetTheSignificator dispose:)
			)
		)
	)
)

(instance sFadeIt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cast eachElementDo: #hide)
				(localproc_1cae)
				(= seconds 2)
			)
			(1 (= ticks 12))
			(2
				(localproc_1cce)
				(= seconds 2)
			)
			(3
				((curRoom script?) cue:)
				(self dispose:)
			)
		)
	)
)

(instance sShowCard of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((= newATurnCard (aTurnCard new:))
					view: local0
					init:
					moveSpeed: 0
					setPri: 250
					setStep: 30 10
					setMotion: MoveTo 148 150 self
				)
			)
			(1 (= seconds 5))
			(2
				(switch local2
					(1
						(newATurnCard
							moveSpeed: 0
							setStep: 30 10
							setScaler: Scaler 100 35 150 110
							setMotion: MoveTo 145 110 self
						)
					)
					(2
						(newATurnCard
							moveSpeed: 0
							setStep: 30 10
							setScaler: Scaler 100 35 150 110
							setMotion: MoveTo 80 110 self
						)
					)
					(3
						(newATurnCard
							moveSpeed: 0
							setStep: 30 10
							setScaler: Scaler 35 100 170 150
							setMotion: MoveTo 144 170 self
						)
					)
					(4
						(newATurnCard
							moveSpeed: 0
							setStep: 30 10
							setScaler: Scaler 100 35 150 110
							setMotion: MoveTo 210 110 self
						)
					)
					(5
						(newATurnCard
							moveSpeed: 0
							setStep: 30 10
							setScaler: Scaler 100 35 150 50
							setMotion: MoveTo 144 50 self
						)
					)
				)
			)
			(3 (sndPlace play: self))
			(4
				(= local2 0)
				(self dispose:)
			)
		)
	)
)

(instance sTurnTheCard of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((= newATurnCard (aTurnCard new:))
					view: local0
					init:
					moveSpeed: 0
					setStep: 60 60
					setMotion: MoveTo 148 150 self
				)
				(switch (vDeck1 x?)
					(255
						(vDeck1 x: 258)
						(vDeck1 x: 261)
						(vDeck1 x: 255)
					)
					(258
						(vDeck1 x: 261)
						(vDeck1 x: 255)
						(vDeck1 x: 258)
					)
					(261
						(vDeck1 x: 255)
						(vDeck1 x: 258)
						(vDeck1 x: 261)
					)
				)
			)
			(1 (self dispose:))
		)
	)
)

(instance aTurnCard of Actor
	(properties
		x 253
		y 50
		priority 196
		fixPriority 1
	)
	
	(method (init)
		(super init: &rest)
		(self ignoreActors: 1 setScaler: Scaler 100 35 150 50)
	)
)

(instance vDeck1 of View
	(properties
		x 245
		y 50
		scaleX 46
		scaleY 46
		priority 152
		fixPriority 1
		view 1000
		signal $4001
		scaleSignal $0001
	)
)

(instance vDeck2 of View
	(properties
		x 249
		y 50
		scaleX 46
		scaleY 46
		priority 153
		fixPriority 1
		view 1000
		signal $4001
		scaleSignal $0001
	)
)

(instance vDeck3 of View
	(properties
		x 253
		y 50
		scaleX 46
		scaleY 46
		priority 154
		fixPriority 1
		view 1000
		signal $4001
		scaleSignal $0001
	)
)

(instance theDeck of Feature
	(properties
		nsLeft 215
		nsTop 7
		nsRight 276
		nsBottom 55
		x 270
		y 190
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4) ((curRoom script?) cue:))
	)
)

(instance sndPlace of Sound
	(properties
		number 969
	)
)
