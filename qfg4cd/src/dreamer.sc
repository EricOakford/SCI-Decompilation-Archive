;;; Sierra Script 1.0 - (do not remove this comment)
(script# 110)
(include sci.sh)
(use Main)
(use GloryRm)
(use Intrface)
(use Array)
(use Print)
(use Actor)
(use System)

(public
	dreamer 0
)

(local
	local0
	local1
	local2
	local3
	local4
)
(procedure (localproc_0096)
	(= local1 (IntArray new: 13))
	(local1 at: 0 (bubble0 init: yourself:))
	(local1 at: 1 (bubble1 init: yourself:))
	(local1 at: 2 (bubble2 init: yourself:))
	(local1 at: 3 (bubble3 init: yourself:))
	(local1 at: 4 (bubble4 init: yourself:))
	(local1 at: 5 (bubble5 init: yourself:))
	(local1 at: 6 (bubble6 init: yourself:))
	(local1 at: 7 (bubble7 init: yourself:))
	(local1 at: 8 (bubble8 init: yourself:))
	(local1 at: 9 (bubble9 init: yourself:))
	(local1 at: 10 (bubble10 init: yourself:))
	(local1 at: 11 (bubble11 init: yourself:))
	(local1 at: 12 (bubble12 init: yourself:))
)

(instance dreamer of GloryRm
	(properties)
	
	(method (init)
		(Bclr 6)
		(if debugging (= dreamNum (GetNumber {dreamnum})))
		(Bset 50)
		(narrator modeless: 1)
		(theGame handsOff: setCursor: theCursor 0)
		(theMusic number: 110 setLoop: -1 play:)
		(switch dreamNum
			(1 (curRoom setScript: dream1))
			(2 (curRoom setScript: dream2))
			(3 (curRoom setScript: dream3))
			(4 (curRoom setScript: dream4))
			(5 (curRoom setScript: dream5))
			(6 (curRoom setScript: dream6))
			(7 (curRoom setScript: dream7))
			(8 (curRoom setScript: dream8))
			(9 (curRoom setScript: dream9))
			(10
				(curRoom setScript: dream10)
			)
			(else 
				(curRoom setScript: dream1)
			)
		)
		(Palette palSET_FLAG 0 255 0)
		(if (OneOf dreamNum 7 9 10)
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
				(= global470 (= global365 (= global366 0)))
				(= gABad2Health (= gABad3Health (= gABad4Health 0)))
			)
			(= number curRoomNum)
			(= perspective picAngle)
			(if (not plane) (= plane thePlane))
			(UpdatePlane
				((curRoom plane?) back: 0 picture: -1 yourself:)
			)
			(RemapColors 2 254 50)
		else
			(= picture 110)
			(super init: &rest)
		)
		(RemapColors 0)
		(Print modeless: 2)
	)
	
	(method (doit &tmp temp0)
		(super doit: &rest)
		(if local0
			(= temp0 0)
			(while (< temp0 13)
				(if (> ((local1 at: temp0) loop?) 4)
					((local1 at: temp0)
						y:
							(if (< ((local1 at: temp0) y?) -1)
								(Random 200 230)
							else
								(- ((local1 at: temp0) y?) 1)
							)
					)
				)
				(++ temp0)
			)
			(if local2
				(= local2 0)
				(= temp0 0)
				(while (< temp0 13)
					(if (< ((local1 at: temp0) loop?) 5)
						((local1 at: temp0)
							y:
								(if (< ((local1 at: temp0) y?) -1)
									(Random 225 260)
								else
									(- ((local1 at: temp0) y?) 1)
								)
						)
					)
					(++ temp0)
				)
			else
				(= local2 1)
			)
		)
	)
	
	(method (dispose)
		(theMusic fade:)
		(narrator modeless: 0)
		(Bclr 50)
		(Unload 128 110 111)
		(Bset 6)
		(Print modeless: 0)
		(super dispose: &rest)
	)
)

(instance dream1 of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			(local4
				(if (> register 100)
					(= local4 (= register 0))
					(self cue:)
				else
					(++ register)
					(Palette palSET_FLAG 0 255 register)
				)
			)
			(register
				(-- register)
				(if (not register) (= local3 1))
				(Palette palSET_FLAG 0 85 register)
				(Palette palSET_FLAG 112 255 register)
			)
			(local3 (= local3 0) (self cue:))
		)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(localproc_0096)
				(= local0 1)
				(= local4 1)
			)
			(1 (= seconds 3))
			(2
				(RemapColors 2 254 50)
				(messager say: 1 6 1 0 self)
			)
			(3
				(Bset 50)
				(RemapColors 0)
				(theMusic number: 111 setLoop: -1 play:)
				(= register 100)
			)
			(4
				(RemapColors 2 254 50)
				(messager say: 2 6 1 0 self)
			)
			(5
				(curRoom newRoom: prevRoomNum)
			)
		)
	)
)

(instance dream2 of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			(local4
				(if (> register 100)
					(= local4 (= register 0))
					(self cue:)
				else
					(++ register)
					(Palette palSET_FLAG 0 255 register)
				)
			)
			(register
				(-- register)
				(if (not register) (= local3 1))
				(Palette palSET_FLAG 0 85 register)
				(Palette palSET_FLAG 112 255 register)
			)
			(local3 (= local3 0) (self cue:))
		)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(localproc_0096)
				(= local0 1)
				(= local4 1)
			)
			(1 (= seconds 3))
			(2
				(RemapColors 2 254 50)
				(messager say: 1 6 2 0 self)
			)
			(3
				(Bset 50)
				(RemapColors 0)
				(theMusic number: 111 setLoop: -1 play:)
				(= register 100)
			)
			(4
				(RemapColors 2 254 50)
				(messager say: 2 6 2 0 self)
			)
			(5
				(curRoom newRoom: prevRoomNum)
			)
		)
	)
)

(instance dream3 of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			(local4
				(if (> register 100)
					(= local4 (= register 0))
					(self cue:)
				else
					(++ register)
					(Palette palSET_FLAG 0 255 register)
				)
			)
			(register
				(-- register)
				(if (not register) (= local3 1))
				(Palette palSET_FLAG 0 85 register)
				(Palette palSET_FLAG 112 255 register)
			)
			(local3 (= local3 0) (self cue:))
		)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(localproc_0096)
				(= local0 1)
				(= local4 1)
			)
			(1 (= seconds 3))
			(2
				(RemapColors 2 254 50)
				(messager say: 1 6 3 0 self)
			)
			(3
				(Bset 50)
				(RemapColors 0)
				(theMusic number: 111 setLoop: -1 play:)
				(= register 100)
			)
			(4
				(RemapColors 2 254 50)
				(messager say: 2 6 3 0 self)
			)
			(5
				(curRoom newRoom: prevRoomNum)
			)
		)
	)
)

(instance dream4 of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			(local4
				(if (> register 100)
					(= local4 (= register 0))
					(self cue:)
				else
					(++ register)
					(Palette palSET_FLAG 0 255 register)
				)
			)
			(register
				(-- register)
				(if (not register) (= local3 1))
				(Palette palSET_FLAG 0 85 register)
				(Palette palSET_FLAG 112 255 register)
			)
			(local3 (= local3 0) (self cue:))
		)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(localproc_0096)
				(= local0 1)
				(= local4 1)
			)
			(1 (= seconds 3))
			(2
				(RemapColors 2 254 50)
				(messager say: 1 6 4 0 self)
			)
			(3
				(Bset 50)
				(RemapColors 0)
				(theMusic number: 111 setLoop: -1 play:)
				(= register 100)
			)
			(4
				(RemapColors 2 254 50)
				(messager say: 2 6 4 0 self)
			)
			(5
				(curRoom newRoom: prevRoomNum)
			)
		)
	)
)

(instance dream5 of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			(local4
				(if (> register 100)
					(= local4 (= register 0))
					(self cue:)
				else
					(++ register)
					(Palette palSET_FLAG 0 255 register)
				)
			)
			(register
				(-- register)
				(if (not register) (= local3 1))
				(Palette palSET_FLAG 0 85 register)
				(Palette palSET_FLAG 112 255 register)
			)
			(local3 (= local3 0) (self cue:))
		)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(localproc_0096)
				(= local0 1)
				(= local4 1)
			)
			(1 (= seconds 3))
			(2
				(RemapColors 2 254 50)
				(messager say: 1 6 5 0 self)
			)
			(3
				(Bset 50)
				(RemapColors 0)
				(theMusic number: 111 setLoop: -1 play:)
				(= register 100)
			)
			(4
				(RemapColors 2 254 50)
				(messager say: 2 6 5 0 self)
			)
			(5
				(curRoom newRoom: prevRoomNum)
			)
		)
	)
)

(instance dream6 of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if local4
			(if (> register 100)
				(= local4 (= register 0))
				(self cue:)
			else
				(++ register)
				(Palette palSET_FLAG 0 255 register)
			)
		)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(Load rsVIEW 111)
				(localproc_0096)
				(= local0 1)
				(= local4 1)
			)
			(1 (= seconds 3))
			(2
				(if (or (== heroType 3) (== heroType 1))
					(RemapColors 2 254 50)
					(messager say: 1 6 7 0 self)
				else
					(RemapColors 2 254 50)
					(messager say: 1 6 8 0 self)
				)
			)
			(3
				(Bset 50)
				(RemapColors 0)
				(erana init:)
				((curRoom plane?) drawPic: 110 12)
				(= seconds 7)
			)
			(4
				(curRoom newRoom: prevRoomNum)
			)
		)
	)
)

(instance dream7 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic number: 111 setLoop: -1 play:)
				(= seconds 4)
			)
			(1
				(RemapColors 2 254 50)
				(Palette palSET_FLAG 0 255 100)
				(messager say: 2 6 9 0 self)
			)
			(2
				(curRoom newRoom: prevRoomNum)
			)
		)
	)
)

(instance dream8 of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			(local4
				(if (> register 100)
					(= local4 (= register 0))
					(self cue:)
				else
					(++ register)
					(Palette palSET_FLAG 0 255 register)
				)
			)
			(register
				(-- register)
				(if (not register) (= local3 1))
				(Palette palSET_FLAG 0 85 register)
				(Palette palSET_FLAG 112 255 register)
			)
			(local3 (= local3 0) (self cue:))
		)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(Load rsVIEW 111)
				(localproc_0096)
				(= local0 1)
				(= local4 1)
			)
			(1 (= seconds 3))
			(2
				(erana init:)
				((curRoom plane?) drawPic: 110 12)
				(= seconds 4)
			)
			(3
				(RemapColors 2 254 50)
				(messager say: 1 6 10 0 self)
			)
			(4
				(Bset 50)
				(RemapColors 0)
				(theMusic number: 111 setLoop: -1 play:)
				(= register 100)
			)
			(5
				(RemapColors 2 254 50)
				(messager say: 2 6 10 0 self)
			)
			(6
				(Bset 50)
				(RemapColors 0)
				(= temp0 0)
				(while (< temp0 13)
					((local1 at: temp0) hide:)
					(++ temp0)
				)
				(erana hide:)
				(= cycles 3)
				(UpdatePlane
					((curRoom plane?) back: 0 picture: -1 yourself:)
				)
			)
			(7 (symbol init:) (= local4 1))
			(8 (= seconds 3))
			(9
				(curRoom newRoom: prevRoomNum)
			)
		)
	)
)

(instance dream9 of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if local4
			(if (> register 100)
				(= local4 (= register 0))
				(self cue:)
			else
				(++ register)
				(Palette palSET_FLAG 0 255 register)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Load rsVIEW 111)
				(theMusic number: 111 setLoop: -1 play:)
				(= seconds 4)
			)
			(1 (symbol init:) (= local4 1))
			(2 (= seconds 3))
			(3
				(RemapColors 2 254 50)
				(messager say: 2 6 11 0 self)
			)
			(4
				(curRoom newRoom: prevRoomNum)
			)
		)
	)
)

(instance dream10 of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if local4
			(if (> register 100)
				(= local4 (= register 0))
				(self cue:)
			else
				(++ register)
				(Palette palSET_FLAG 0 255 register)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Load rsVIEW 111)
				(theMusic number: 111 setLoop: -1 play:)
				(= seconds 4)
			)
			(1 (symbol init:) (= local4 1))
			(2 (= seconds 3))
			(3
				(RemapColors 2 254 50)
				(messager say: 2 6 12 0 self)
			)
			(4
				(curRoom newRoom: prevRoomNum)
			)
		)
	)
)

(instance bubble0 of Actor
	(properties
		x 245
		y 135
		priority 10
		fixPriority 1
		view 110
		loop 9
		signal $6001
	)
)

(instance bubble1 of Actor
	(properties
		x 68
		y 74
		priority 6
		fixPriority 1
		view 110
		signal $6001
	)
)

(instance bubble2 of Actor
	(properties
		x 94
		y 46
		priority 6
		fixPriority 1
		view 110
		loop 1
		signal $6001
	)
)

(instance bubble3 of Actor
	(properties
		x 89
		y 106
		priority 6
		fixPriority 1
		view 110
		loop 2
		signal $6001
	)
)

(instance bubble4 of Actor
	(properties
		x 187
		y 31
		priority 10
		fixPriority 1
		view 110
		loop 11
		signal $6001
	)
)

(instance bubble5 of Actor
	(properties
		x 59
		y 161
		priority 6
		fixPriority 1
		view 110
		loop 4
		signal $6001
	)
)

(instance bubble6 of Actor
	(properties
		x 75
		y 142
		priority 10
		fixPriority 1
		view 110
		loop 5
		signal $6001
	)
)

(instance bubble7 of Actor
	(properties
		x 267
		y 56
		priority 10
		fixPriority 1
		view 110
		loop 6
		signal $6001
	)
)

(instance bubble8 of Actor
	(properties
		x 231
		y 67
		priority 10
		fixPriority 1
		view 110
		loop 7
		signal $6001
	)
)

(instance bubble9 of Actor
	(properties
		x 273
		y 173
		priority 10
		fixPriority 1
		view 110
		loop 8
		signal $6001
	)
)

(instance bubble10 of Actor
	(properties
		x 318
		y 102
		priority 10
		fixPriority 1
		view 110
		loop 10
		signal $6001
	)
)

(instance bubble11 of Actor
	(properties
		x 255
		y 141
		priority 6
		fixPriority 1
		view 110
		loop 3
		signal $6001
	)
)

(instance bubble12 of Actor
	(properties
		x 315
		y 8
		priority 6
		fixPriority 1
		view 110
		loop 3
		signal $6001
	)
)

(instance erana of View
	(properties
		y 1
		view 111
		signal $6000
	)
)

(instance symbol of View
	(properties
		x 153
		y 96
		view 111
		loop 2
		signal $6000
	)
)
