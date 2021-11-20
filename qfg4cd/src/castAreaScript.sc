;;; Sierra Script 1.0 - (do not remove this comment)
(script# 12)
(include sci.sh)
(use Main)
(use Array)
(use Sound)
(use Motion)
(use System)

(public
	castAreaScript 0
)

(local
	gTheObj_2Loop
	gTheObj_2Cel
	local2
	local3
)
(instance castAreaScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local2 (IntArray with: 2 3 0 3 0 1 2 3))
				(= local3 (IntArray with: 0 0 0 1 0 0 2 3))
				(theGame handsOff:)
				(= gTheObj_2Loop (ego loop?))
				(switch register
					(83 (soundFX number: 940 play:))
					(85 (soundFX number: 936 play:))
					(81 (soundFX number: 942 play:))
					(else 
						(soundFX number: 934 play:)
					)
				)
				(if
					(and
						(> (ego view?) 17)
						(< (ego view?) 21)
						(< (ego view?) 21)
					)
					(= gTheObj_2Cel (ego cel?))
					(ego view: 19 loop: (local3 at: gTheObj_2Loop))
				else
					(ego view: 15 loop: (local2 at: gTheObj_2Loop))
				)
				(ego setMotion: 0 setCycle: End self)
			)
			(1
				(if
					(and
						(== register 85)
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
						)
					)
					(if (== curRoomNum 580)
						(curRoom doVerb: 85)
					else
						((ScriptID 50) doVerb: 85)
					)
				)
				(ego setCycle: Beg self)
			)
			(2
				(if
					(and
						(> (ego view?) 17)
						(< (ego view?) 21)
						(< (ego view?) 21)
					)
					(ego view: 20 loop: gTheObj_2Loop cel: gTheObj_2Cel)
				else
					(ego normalize: gTheObj_2Loop)
				)
				(if (and (== register 85) (== curRoomNum 580))
					(curRoom cue:)
				else
					(theGame handsOn:)
				)
				(switch register
					(83
						(Palette palSET_FLAG 0 255 500)
						(Palette palSET_FLAG 0 255 100)
					)
					(85
						(cond 
							(
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
								)
							)
							(
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
									270
									340
									460
									545
									710
									750
									730
									632
								)
								(curRoom doVerb: register)
							)
							(else (messager say: 0 0 4 0 0 12))
						)
					)
					(81
						(if
							(OneOf
								curRoomNum
								250
								270
								290
								300
								320
								340
								360
								780
								440
								480
								510
								545
								579
								580
								593
								600
								800
								720
								740
								750
								770
								730
								530
								535
								541
								542
								543
								545
							)
							(curRoom doVerb: register)
						else
							(messager say: 0 0 1 0 0 12)
						)
					)
					(82
						(if
							(OneOf
								curRoomNum
								270
								290
								340
								440
								460
								520
								580
								593
								600
								641
								643
								650
								750
								800
							)
							(curRoom doVerb: register)
						else
							(messager say: 0 0 2 0 0 12)
						)
					)
				)
				(= register 0)
				(local2 dispose:)
				(local3 dispose:)
				(self dispose:)
			)
		)
	)
)

(instance soundFX of Sound
	(properties)
)
