;;; Sierra Script 1.0 - (do not remove this comment)
(script# 21)
(include game.sh)
(use Main)
(use SQRoom)
(use Sq4Narrator)
(use Sq4Feature)
(use RandCyc)
(use LoadMany)
(use Motion)
(use System)

(public
	rm021 0
)

(local
	[sizeBuf 4]
	local4
)
(procedure (Measure theString)
	(TextSize @sizeBuf theString 0 315)
	(return (/ (- 180 (- [sizeBuf 2] [sizeBuf 0])) 2))
)

(instance rm021 of SQRoom
	(properties
		picture 21
	)
	
	(method (init)
		(super init:)
		(Load SOUND 823)
		(Load VIEW 24)
		(LoadMany PICTURE 803 21)
		(Load FONT 0)
		(p1 init: setCycle: RandCycle)
		(p2 init: setCycle: Forward)
		(p3 init: setCycle: Forward)
		(ShowStatus 4)
		(if (!= prevRoomNum 540) (self setRegions: 707))
		(switch prevRoomNum
			(20
				(music number: 823 loop: 1 play:)
				(self setScript: newRmScript)
			)
			(540
				(self setScript: endScript 0 450)
			)
			(else 
				(music number: 823 loop: 1 play:)
				(self setScript: newRmScript)
			)
		)
	)
)

(instance rmScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= register 700)
				(= cycles 2)
			)
			(1
				(if (and (-- register) (== newRoomNum curRoomNum))
					(Palette PALCycle 176 207 1)
					(if (== register 100) (music fade:))
					(-- state)
				)
				(= cycles 1)
			)
			(2 (curRoom newRoom: 59))
		)
	)
)

(instance newRmScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(Palette PALCycle 176 207 1)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0 (= seconds 7))
			(1 (music fade: self))
			(2 (curRoom newRoom: 59))
		)
	)
)

(instance endScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theIconBar disable:)
				(= seconds 1)
			)
			(1
				(Palette PALCycle 176 207 1)
				(Animate (cast elements?) TRUE)
				(if (-- register) (-- state))
				(= cycles 1)
			)
			(2
				(cast eachElementDo: #hide)
				(curRoom drawPic: 803 IRISIN)
				(= cycles 10)
			)
			(3
				(tCredits talkerNum: 99 noun: 99 nMsgType: 3 say: 1 self)
			)
			(4
				(tCredits talkerNum: 97 nMsgType: 1 say: 3 self)
			)
			(5
				(tCredits talkerNum: 97 noun: 97 nMsgType: 1 say: 1 self)
			)
			(6 (tCredits say: 4 self))
			(7 (tCredits say: 5 self))
			(8 (tCredits say: 7 self))
			(9 (tCredits say: 8 self))
			(10 (tCredits say: 9 self))
			(11
				(if (== local4 5)
					(= quit 1)
				else
					(if (>= (++ local4) 1)
						(theIconBar enable:)
						(HandsOff)
					)
					(= cycles 1)
				)
			)
			(12 (= start 4) (self init:))
		)
	)
)

(instance p1 of Sq4Prop
	(properties
		x 155
		y 81
		view 24
		cycleSpeed 4
	)
)

(instance p2 of Sq4Prop
	(properties
		x 214
		y 86
		view 24
		loop 1
		cel 2
		cycleSpeed 3
	)
)

(instance p3 of Sq4Prop
	(properties
		x 85
		y 146
		view 24
		loop 2
		cel 2
		cycleSpeed 3
	)
)

(instance tCredits of Sq4Narrator
	(properties
		noun PRINTMSG
		modeless TRUE
		talkerNum PRINTMSG
		nMsgType 1
	)
	
	(method (dispose)
		(= returnVal 0)
		(curRoom drawPic: 803 SCROLLUP)
		(super dispose: &rest)
	)
	
	(method (display theText &tmp theY)
		(= theY (Measure theText))
		(= ticks (Max 240 (/ ticks 2)))
		(Display theText
			p_color myTextColor
			p_at 3 theY
			p_width 315
			p_font SYSFONT
			p_mode teJustCenter
		)
	)
)
