;;; Sierra Script 1.0 - (do not remove this comment)
(script# 200)
(include sci.sh)
(use Main)
(use Game)
(use System)

(public
	rm200 0
)

(local
	local0
	local1
	[local2 20]
)
(instance rm200 of Rm
	(properties
		picture 200
		horizon 66
		picAngle 15
	)
	
	(method (init)
		(if (!= (cSound signal?) -1)
			(cSound client: curRoom fade: 0 1 10 1)
		else
			(self cue:)
		)
		(Bclr 6)
		(if (= global449 (PalVary pvGET_CURRENT_STEP))
			(PalVary pvPAUSE_RESUME 1)
		)
		(switch prevRoomNum
			(150 (= style -32759))
			(210 (= style -32761))
			(420
				(= style -32759)
				(self cue:)
			)
			(620
				(= style -32759)
				(self cue:)
			)
			(else 
				(= style -32759)
				(if (OneOf prevRoomNum 110 120 100) (self cue:))
			)
		)
		(super init:)
		(self setScript: showTarnaFarShot)
	)
	
	(method (dispose)
		(if global449 (PalVary pvPAUSE_RESUME 0))
		(Bset 6)
		(super dispose:)
	)
	
	(method (cue)
		(cSound number: 911 setLoop: 1 play: showTarnaFarShot)
	)
)

(instance showTarnaFarShot of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theIconBar disable:)
				(SetCursor 0)
				(= cycles 2)
			)
			(1
				(DrawCel 200 0 0 125 171 15)
				(Message msgGET 200 0 0 1 1 @local2)
				(Display
					@local2
					dsCOORD
					140
					173
					dsALIGN
					1
					dsCOLOR
					0
					dsFONT
					123
				)
				(Display
					@local2
					dsCOORD
					139
					172
					dsALIGN
					1
					dsCOLOR
					29
					dsFONT
					123
				)
				(switch prevRoomNum
					(120 (= local0 280))
					(150 (= local0 210))
					(420 (= local0 210))
					(620 (= local0 210))
					(else  (= local0 210))
				)
			)
			(2
				(theIconBar enable:)
				(SetCursor 1)
				(curRoom newRoom: local0)
			)
		)
	)
)
