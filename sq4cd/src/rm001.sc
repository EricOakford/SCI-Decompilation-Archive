;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1)
(include game.sh)
(use Main)
(use SQRoom)
(use Sq4Feature)
(use LoadMany)
(use Motion)
(use System)

(public
	rm001 0
)

(local
	[local0 2]
)
(instance rm001 of SQRoom
	(properties
		picture 106
	)
	
	(method (init)
		(super init:)
		(Load PICTURE 1)
		(LoadMany VIEW 101)
		(LoadMany FONT 68 69)
		(self setScript: rmScript setRegions: INTRO)
	)
)

(instance rmScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(cond 
			(
			(and (== state 1) (== (music prevSignal?) 10)) (self cue:))
			(register
				(Palette PALCycle 160 191 -1)
				(Palette PALCycle 128 159 -1)
			)
		)
	)
	
	(method (changeState newState &tmp [str 50])
		(switch (= state newState)
			(0
				(music loop: -1 playBed:)
				(= cycles 1)
			)
			(1 0)
			(2
				(curRoom drawPic: 1)
				(four init: hide:)
				(= register 1)
				(= seconds 3)
			)
			(3
				(four show: setCycle: EndLoop self)
			)
			(4
				(Message MsgGet 1 97 0 1 1 @str)
				(DoDisplay @str
					#at 1 177
					#width 316
					#color myRgtBordColor
					#back myLowlightColor
					#mode teJustCenter
					#font 68
				)
				(four stopUpd:)
				(= cycles 1)
			)
			(5 (= seconds 5))
			(6
				(curRoom drawPic: 803 FADEOUT)
				(if (== curRoomNum newRoomNum) (curRoom newRoom: 6))
			)
		)
	)
)

(instance four of Sq4Prop
	(properties
		x 156
		y 129
		view 101
	)
)
