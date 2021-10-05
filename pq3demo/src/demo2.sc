;;; Sierra Script 1.0 - (do not remove this comment)
(script# 121)
(include game.sh)
(use Main)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	demo2 0
)

(instance demo2 of Room
	(properties
		picture 1
		style FADEOUT
	)
	
	(method (init)
		(HandsOff)
		(LoadMany PICTURE 100 98 3 2 82)
		(LoadMany VIEW 850 851 105)
		(theMusic number: 50 setLoop: -1 play:)
		(super init:)
		(switch prevRoomNum
			(120
				(self setScript: rmScript)
				(III init: hide:)
			)
			(else 
				(self overlay: 100 setScript: demoScript)
				(III init:)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
			(else (event claimed: TRUE))
		)
	)
)

(instance cycleScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Palette PALCycle 208 213 1)
				(Palette PALCycle 213 218 1)
				(Palette PALCycle 218 223 1)
				(Palette PALCycle 223 228 1)
				(Palette PALCycle 229 234 -1)
				(Palette PALCycle 234 239 -1)
				(Palette PALCycle 239 244 -1)
				(Palette PALCycle 244 249 -1)
				(= cycles 1)
				(= state -1)
			)
		)
	)
)

(instance rmScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(sfx1 number: 936 setLoop: -1 play: fade: 95 40 10 0)
				(self setScript: cycleScript)
				(= cycles 1)
			)
			(1
				(if (< (theMusic prevSignal?) 10) (-- state))
				(= cycles 1)
			)
			(2
				(curRoom style: PLAIN overlay: 100)
				(= cycles 1)
			)
			(3
				(if (< (theMusic prevSignal?) 20) (-- state))
				(= cycles 1)
			)
			(4 (III show:) (= seconds 7))
			(5 (sfx1 fade:) (= cycles 1))
			(6
				(III dispose:)
				(curRoom style: WIPERIGHT drawPic: 1 overlay: 82)
				(= seconds 10)
			)
			(7
				(curRoom style: FADEOUT drawPic: 98)
				(= cycles 2)
				(self setScript: 0)
			)
			(8
				(curRoom style: WIPELEFT drawPic: 3)
				(head2 init: stopUpd:)
				(head3 init: stopUpd:)
				(= cycles 2)
			)
			(9
				(credits2 init: stopUpd:)
				(= seconds 3)
			)
			(10 (head2 setCycle: EndLoop self))
			(11
				(head2 stopUpd:)
				(head3 setCycle: EndLoop self)
			)
			(12 (= seconds 2))
			(13
				(credits2 dispose:)
				(head2 dispose:)
				(head3 dispose:)
				(curRoom style: WIPERIGHT drawPic: 2)
				(head1 init:)
				(= cycles 2)
			)
			(14
				(credit init:)
				(= seconds 3)
			)
			(15 (head1 setCycle: EndLoop self))
			(16 (= seconds 6))
			(17 (theMusic fade: self))
			(18 (curRoom newRoom: 122))
		)
	)
)

(instance demoScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(self setScript: cycleScript)
				(sfx1 number: 936 setLoop: -1 play: fade: 127 25 10 0)
				(= seconds 15)
			)
			(1
				(III dispose:)
				(curRoom style: WIPERIGHT drawPic: 1 overlay: 82)
				(= seconds 10)
			)
			(2 (theMusic fade: self))
			(3 (curRoom newRoom: 120))
		)
	)
)

(instance III of Prop
	(properties
		x 161
		y 172
		description {III}
		view 105
		priority 10
		signal stopUpdOn
	)
)

(instance sfx1 of Sound)

(instance head1 of Prop
	(properties
		x 211
		y 163
		view 850
	)
)

(instance credit of Prop
	(properties
		x 98
		y 65
		view 850
		loop 1
		signal stopUpdOn
	)
)

(instance head2 of Prop
	(properties
		x 74
		y 161
		view 851
		signal ignrAct
	)
)

(instance head3 of Prop
	(properties
		x 39
		y 51
		view 851
		loop 1
	)
)

(instance credits2 of Prop
	(properties
		x 211
		y 92
		view 851
		loop 2
	)
)
