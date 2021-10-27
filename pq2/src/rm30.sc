;;; Sierra Script 1.0 - (do not remove this comment)
(script# 30)
(include sci.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm30 0
)

(local
	waiter
	newProp_6
	newProp
	newProp_2
	newProp_7
	newView
	[newProp_8 4]
	newProp_3
	newProp_4
	newProp_5
	local13
	local14
	local15
	local16
	local17
	local18
	local19
	local20
	local21
	local22
	local23
	local24
	local25
	local26
	local27
	local28
	local29
	local30
	local31
	local32
	local33
	local34
	local35
	local36
	local37
	local38
	local39
	local40
	local41
	local42
	local43
	local44
	local45
)
(procedure (localproc_000c)
	(Print &rest #at -1 125)
)

(procedure (localproc_001b)
	(if (== (Random 1 8) 1)
		(coughScript changeState: 0)
		(= local14 20)
	)
	(if (== (Random 1 8) 2)
		(coughScript changeState: 2)
		(= local15 20)
	)
	(if (== (Random 1 8) 3)
		(coughScript changeState: 4)
		(= local16 20)
	)
	(if (== (Random 1 8) 4)
		(coughScript changeState: 6)
		(= local17 20)
	)
)

(instance mariesTheme of Sound
	(properties
		number 14
		priority 5
	)
)

(instance dinnerMusic of Sound
	(properties
		number 8
		priority 1
	)
)

(instance rm30 of Room
	(properties
		picture 30
		style $0000
	)
	
	(method (init)
		(super init:)
		(Load rsVIEW 1)
		(Load rsVIEW 0)
		(Load rsVIEW 266)
		(Load rsVIEW 28)
		(= gunFireState 3)
		(= local42 16)
		(= local43 8)
		(= local44 5)
		((= newProp (Prop new:)) init:)
		((= newProp_2 (Prop new:)) init:)
		((= newProp_3 (Prop new:)) init:)
		((= newProp_4 (Prop new:)) init:)
		((= newProp_5 (Prop new:)) init:)
		(dinnerMusic play:)
		(= gunDrawn 0)
		(ego
			view: 1
			setStep: 3 2
			init:
			posn: 107 165
			setMotion: MoveTo 107 143
			setScript: day2Script
		)
		((= waiter (Actor new:))
			view: 28
			posn: 137 104
			init:
			setCycle: Walk
			setStep: 3 2
			setScript: waiterScript
		)
		((= newProp_6 (Prop new:))
			view: 266
			posn: 178 111
			loop: 8
			cel: 0
			init:
			stopUpd:
			setScript: marieScript
		)
		((= newView (View new:))
			view: 266
			posn: 0 0
			loop: 4
			cel: 7
			setPri: 15
			init:
			stopUpd:
		)
		((= newProp_7 (Prop new:))
			view: 266
			loop: 7
			cel: 5
			posn: 98 78
			setPri: 8
			init:
			ignoreActors:
		)
		((View new:)
			view: 266
			posn: 224 133
			loop: 3
			cel: 4
			setPri: 9
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 266
			posn: 206 130
			loop: 3
			cel: 3
			setPri: 9
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 266
			posn: 123 111
			loop: 3
			cel: 2
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 266
			posn: 149 112
			loop: 3
			cel: 1
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 266
			posn: 268 105
			loop: 4
			cel: 2
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 266
			posn: 59 100
			loop: 4
			cel: 2
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 266
			posn: 194 91
			loop: 4
			cel: 2
			setPri: 7
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 266
			posn: 134 91
			loop: 4
			cel: 2
			setPri: 7
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 266
			posn: 200 109
			loop: 4
			cel: 2
			setPri: 9
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 266
			posn: 187 130
			loop: 2
			cel: 0
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 266
			posn: 48 111
			loop: 4
			cel: 1
			setPri: 9
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 266
			posn: 215 110
			loop: 1
			cel: 0
			ignoreActors:
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 266
			posn: 102 98
			loop: 3
			cel: 0
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 266
			posn: 56 98
			loop: 4
			cel: 0
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 266
			posn: 271 86
			loop: 4
			cel: 4
			init:
			stopUpd:
			addToPic:
		)
		((= [newProp_8 0] (Prop new:))
			view: 266
			posn: 0 0
			loop: 3
			cel: 5
			setPri: 10
			init:
			stopUpd:
		)
		((= [newProp_8 1] (Prop new:))
			view: 266
			posn: 0 0
			loop: 3
			cel: 6
			setPri: 10
			init:
			stopUpd:
		)
		((= [newProp_8 2] (Prop new:))
			view: 266
			posn: 0 0
			loop: 3
			cel: 7
			setPri: 10
			init:
			stopUpd:
		)
		((= [newProp_8 3] (Prop new:))
			view: 266
			posn: 0 0
			loop: 3
			cel: 8
			setPri: 10
			init:
			stopUpd:
		)
	)
	
	(method (doit)
		(cond 
			((> local14 1) (-- local14))
			((== local14 1) (= local14 0) (coughScript changeState: 1))
			((> local15 1) (-- local15))
			((== local15 1) (= local15 0) (coughScript changeState: 3))
			((> local19 0) (-- local19))
			((> local20 1) (-- local20))
			((== local20 1) (waiterScript changeState: 16) (= local20 0))
			((> local16 1) (-- local16))
			((== local16 1) (= local16 0) (coughScript changeState: 5))
			((> local17 1) (-- local17))
			((== local17 1) (= local17 0) (coughScript changeState: 7))
			((> local18 1) (-- local18))
			((== local18 1) (= local18 0) (day2Script changeState: 1))
		)
		(if (> local13 1) (-- local13))
		(if (== local13 1)
			(= local13 0)
			(if local24
				(waiterScript changeState: 6)
			else
				(= local13 100)
			)
		)
		(cond 
			(
			(and (!= (mod (ego view?) 2) 0) (<= (ego y?) 123)) (ego view: (- (ego view?) 1)))
			(
			(and (!= (mod (ego view?) 2) 1) (> (ego y?) 123)) (ego view: (+ (ego view?) 1)))
		)
		(cond 
			((> (ego y?) 165) (= dateState 5) (curRoom newRoom: 105))
			(
				(and
					(ego inRect: 95 99 215 109)
					(== (newProp_7 cel?) 5)
				)
				(localproc_001b)
				(newProp_7 setCycle: EndLoop startUpd:)
				(if (not local32)
					(newView posn: 157 164)
					(= local32 1)
				)
			)
			(
			(and (not (ego inRect: 95 99 215 109)) local32) (newView posn: 0 0) (= local32 0))
			((> (newProp x?) 0) (if (not local24) (= local24 1)))
			((and (> (ego x?) 0) local24) (= local24 0))
		)
		(cond 
			(
				(and
					(< (ego x?) 120)
					(> (ego y?) 130)
					local31
					(not local39)
				)
				(waiterScript changeState: 24)
			)
			((and (< (ego x?) 140) (not local24) local33) (if (not local34) (localproc_000c 30 0)) (= local33 0))
			(
			(and (ego inRect: 170 111 223 120) (not local22))
				(= local22 1)
				(mariesTheme play:)
				(localproc_000c 30 1)
				(localproc_000c 30 2)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(coughScript dispose:)
		(marieScript dispose:)
		(sonnyScript dispose:)
		(waiterScript dispose:)
		(day2Script dispose:)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(evSAID
				(cond 
					((Said 'look>')
						(cond 
							((Said '[<at,around][/!*,chamber,cafe,arnie]') (localproc_000c 30 3))
							((Said '<below/table') (localproc_000c 30 4))
							((Said '/table') (localproc_000c 30 5))
							((Said '/wall') (Print 30 6 #at -1 125))
							((or (Said '<up') (Said '/ceiling')) (localproc_000c 30 7))
							((or (Said '<down') (Said '/floor')) (localproc_000c 30 8))
							((Said '/partition') (localproc_000c 30 9))
							((Said '/counter') (localproc_000c 30 10))
							((Said '/phone') (Print 30 11))
							((Said '/desk') (localproc_000c 30 12))
							((Said '/chow')
								(if local31
									(localproc_000c 30 13)
								else
									(localproc_000c 30 14)
								)
							)
							((Said '/dude')
								(if (<= (waiter distanceTo: ego) 20)
									(localproc_000c 30 15)
								else
									(localproc_000c 30 16)
								)
							)
							((Said '/crowd,couple,customer') (localproc_000c 30 16))
							((Said '/waiter')
								(if
								(and (< (waiter y?) 105) (> (waiter x?) 105))
									(localproc_000c 30 17)
								else
									(localproc_000c 30 15)
								)
							)
							((Said '/broad,cheeks')
								(cond 
									(
									(and (not local24) (not (ego inRect: 170 111 223 120))) (localproc_000c 30 18))
									(
									(and (not local24) (ego inRect: 170 111 223 120)) (localproc_000c 30 19))
									(else (localproc_000c 30 20))
								)
							)
							((Said '/computer,terminal,register') (localproc_000c 30 21))
							((Said '/plant') (localproc_000c 30 22))
							((Said '/painting,print') (localproc_000c 30 23))
							((Said '/grass') (localproc_000c 30 24))
							((Said '/pane') (localproc_000c 30 25))
						)
					)
					((Said 'look,read/sign')
						(if (or (< (ego x?) 160) local24)
							(localproc_000c 30 26)
						else
							(localproc_000c 30 27)
						)
					)
					((Said 'frisk[<behind]/partition') (localproc_000c 30 28))
					((Said 'frisk/customer,cafe,dude,broad,couple') (localproc_000c 30 29))
					(
						(or
							(Said '(use,dial,(pick[<up]))<phone')
							(Said 'make/call')
						)
						(localproc_000c 30 30)
					)
					((Said 'open/register') (localproc_000c 30 31))
					((Said 'get/plant') (localproc_000c 30 32))
					((Said 'get/register') (localproc_000c 30 33))
					((Said 'affirmative')
						(if local24
							(localproc_000c 30 34)
						else
							(localproc_000c 30 35)
						)
					)
					((Said 'n')
						(if local24
							(localproc_000c 30 36)
						else
							(localproc_000c 30 37)
						)
					)
					((Said 'beg')
						(cond 
							((> (ego x?) 105) (localproc_000c 30 38))
							(local24 (localproc_000c 30 39))
							(else (localproc_000c 30 40))
						)
					)
					((Said 'chat>')
						(cond 
							((Said '/dude,crowd,customer,cafe,customer')
								(cond 
									((> (ego x?) 105)
										(switch (Random 0 5)
											(0 (Print 30 41 #at -1 125))
											(1 (localproc_000c 30 42))
											(2 (localproc_000c 30 43))
											(3 (localproc_000c 30 44))
											(4 (localproc_000c 30 45))
											(5 (localproc_000c 30 46))
										)
									)
									((not local24) (localproc_000c 30 47))
									(else (localproc_000c 30 48))
								)
							)
							((Said '/waiter')
								(if
								(and (< (waiter y?) 105) (> (waiter x?) 105))
									(localproc_000c 30 49)
								else
									(localproc_000c 30 50)
								)
							)
							((Said '/broad,cheeks,cheeks')
								(cond 
									((not local24) (localproc_000c 30 51))
									((and local31 (not local37)) (localproc_000c 30 52))
									(local35 (localproc_000c 30 53))
									(else (localproc_000c 30 54) (localproc_000c 30 55))
								)
							)
							(local24 (localproc_000c 30 56))
							(else (localproc_000c 30 57))
						)
						(event claimed: 1)
					)
					((Said 'kiss/!*') (localproc_000c 30 58))
					(
						(or
							(Said 'kiss/cheeks,broad,cheeks')
							(Said 'gave/kiss/broad')
						)
						(cond 
							((and (not local26) local24) (marieScript changeState: 4))
							((ego inRect: 0 0 320 200) (localproc_000c 30 51))
							(local35 (localproc_000c 30 59))
							(else (localproc_000c 30 60))
						)
					)
					(
					(Said 'eat,blow,fuck/couple,customer,cafe,customer,dude') (Print 30 61 #at -1 125))
					((Said 'fuck,eat,blow/broad,cheeks,cheeks')
						(if (> (newProp x?) 0)
							(switch (Random 0 3)
								(0 (localproc_000c 30 62))
								(1 (localproc_000c 30 63))
								(2 (localproc_000c 30 64))
								(3 (localproc_000c 30 65))
							)
						else
							(localproc_000c 30 66)
						)
					)
					(
						(or
							(Said 'ask/cost[<chow,lobster,loaf,rib]')
							(Said 'ask/dude,waiter/cost')
							(Said 'much<how')
						)
						(if (and local35 (not local28))
							(localproc_000c 30 67)
							(= local38 1)
						else
							(localproc_000c 30 68)
						)
					)
					(
						(or
							(Said 'read,ask/chow,choice,menu')
							(Said 'look,gave,display,get/menu[/i]')
							(Said 'ask/waiter/menu,chow,choice')
						)
						(if local35
							(localproc_000c 30 67)
						else
							(localproc_000c 30 69)
						)
					)
					((Said 'display/badge')
						(cond 
							((and local25 local38 (not local28))
								(localproc_000c 30 70)
								(= local42 8)
								(= local43 4)
								(= local44 3)
							)
							((or (ego inRect: 170 111 223 120) local24) (localproc_000c 30 71))
							((> (ego x?) 105)
								(switch (Random 0 3)
									(0 (localproc_000c 30 72))
									(1 (localproc_000c 30 73))
									(2 (localproc_000c 30 74))
									(3 (localproc_000c 30 75))
								)
							)
							(else (localproc_000c 30 76))
						)
					)
					(
						(or
							(Said '[exit]/info[/waiter]')
							(Said 'info[/waiter]')
						)
						(if local37
							(if (> dollars 5)
								(localproc_000c 30 77)
								(= dollars (- dollars 5))
							else
								(localproc_000c 30 78)
							)
						else
							(localproc_000c 30 79)
						)
					)
					(
						(or
							(Said 'pay/waiter,bill,chow,chow')
							(Said 'get,get/bill,check')
						)
						(if local37
							(if local24
								(waiterScript changeState: 6)
							else
								(waiterScript changeState: 28)
							)
						else
							(localproc_000c 30 80)
						)
					)
					((Said '[order]/chow')
						(if (not local28)
							(= local41 3)
							(= local40 (+ local42 local42))
						)
						(waiterScript changeState: 11)
					)
					((Said '[order]/lobster')
						(if (not local28)
							(= local41 3)
							(= local40 (+ local42 local42))
						)
						(waiterScript changeState: 11)
					)
					((Said '[order]/rib[<prime]')
						(if (not local28)
							(= local41 2)
							(= local40 (+ local42 local43))
						)
						(waiterScript changeState: 11)
					)
					(
						(or
							(Said '[order]/meatloaf')
							(Said '[order]/loaf<meat')
						)
						(if (not local28)
							(= local41 1)
							(= local40 (+ local42 local44))
						)
						(waiterScript changeState: 11)
					)
					(
					(or (Said 'get,get/chow') (Said '[call,get]/waiter'))
						(cond 
							(
								(and
									local28
									local29
									(not local31)
									(not local37)
									(not local30)
									local24
									(== local19 0)
									(> local20 1)
								)
								(= local20 0)
								(waiterScript changeState: 16)
							)
							((and local28 (> local19 0)) (localproc_000c 30 81))
							((and (not local24) (not local28)) (localproc_000c 30 82))
							((and local24 (not local28)) (localproc_000c 30 83))
							((and local31 (not local37)) (localproc_000c 30 84))
							((not local29) (localproc_000c 30 85))
							((and local30 (not local31)) (localproc_000c 30 86))
							(local37
								(if local24
									(waiterScript changeState: 6)
								else
									(waiterScript changeState: 28)
								)
							)
							(else (localproc_000c 30 87))
						)
					)
					((Said '[order]/water,tea,coffee,coca')
						(if local24
							(localproc_000c 30 88)
						else
							(localproc_000c 30 89)
						)
					)
					((Said '[order]/beer,booze,drink')
						(if local37
							(localproc_000c 30 90)
						else
							(localproc_000c 30 91)
						)
					)
					(
					(Said 'eat[/chow,chow,meatloaf,loaf,rib,lobster]')
						(cond 
							(
							(and local31 local24 (not local36) (not local37)) (marieScript changeState: 1))
							((and (not local24) (not local37)) (localproc_000c 30 92))
							((not local24) (localproc_000c 30 89))
							(local37 (localproc_000c 30 93))
							(local36 (localproc_000c 30 94))
							(else (localproc_000c 30 95))
						)
					)
					(
					(Said 'gave/flower,rose,plant,bouquet,carnation')
						(cond 
							((and local24 (not local26) (ego has: 11)) (sonnyScript changeState: 2))
							((and local24 local27) (localproc_000c 30 96))
							((not local24) (localproc_000c 30 97))
							((and local26 (ego has: 11)) (localproc_000c 30 98))
							(else (localproc_000c 30 99))
						)
					)
					((Said 'sat[<down]')
						(cond 
							((ego inRect: 203 105 233 120) (sonnyScript changeState: 0))
							((ego inRect: 170 111 203 120) (localproc_000c 30 100))
							(local24 (localproc_000c 30 101))
							(else (localproc_000c 30 102))
						)
					)
					(
						(or
							(Said 'say,tell/bye,cheeks')
							(Said '/bye')
							(Said '/cheeks<bye')
						)
						(= local34 1)
						(localproc_000c 30 103)
					)
					((Said 'stand,(get<up)')
						(if (not local24)
							(localproc_000c 30 104)
						else
							(sonnyScript changeState: 4)
						)
					)
				)
			)
		)
	)
)

(instance coughScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 ([newProp_8 0] posn: 121 54))
			(1 ([newProp_8 0] posn: 0 0))
			(2 ([newProp_8 1] posn: 112 69))
			(3 ([newProp_8 1] posn: 0 0))
			(4 ([newProp_8 2] posn: 92 88))
			(5 ([newProp_8 2] posn: 0 0))
			(6 ([newProp_8 3] posn: 77 60))
			(7 ([newProp_8 3] posn: 0 0))
		)
	)
)

(instance waiterScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canControl: 0)
				(waiter setMotion: MoveTo 103 104 self startUpd:)
			)
			(1
				(waiter setMotion: MoveTo 103 115 self)
			)
			(2
				(waiter stopUpd: (Print 30 105 #at 68 150))
				(self cue:)
				(User canControl: 1)
			)
			(3
				(waiter setMotion: MoveTo 103 104 self startUpd:)
			)
			(4
				(waiter setMotion: MoveTo 127 104 self)
			)
			(5 (waiter stopUpd:))
			(6
				(User canControl: 0 canInput: 0)
				(waiter
					ignoreActors: 1
					illegalBits: 0
					setCycle: Walk
					setLoop: -1
					setStep: 3 2
					setMotion: MoveTo 103 104 self
					startUpd:
				)
			)
			(7
				(waiter setMotion: MoveTo 103 113 self)
			)
			(8
				(waiter setPri: 8 setMotion: MoveTo 194 113 self)
			)
			(9
				(waiter setMotion: MoveTo 194 112 self)
			)
			(10
				(waiter stopUpd:)
				(User canInput: 1)
				(if local37
					(self changeState: 28)
				else
					(Print 30 106 #at 59 140 #time 6)
					(Print 30 107 #at 59 150)
					(= local35 1)
				)
			)
			(11
				(cond 
					((and local24 local35 (not local28))
						(switch local41
							(3 (localproc_000c 30 108))
							(1 (localproc_000c 30 109))
							(2 (localproc_000c 30 110))
						)
						(localproc_000c 30 111)
						(= local28 1)
						(= local19 300)
						(= local20 450)
						(self cue:)
					)
					(local28 (localproc_000c 30 112))
					((not local35) (localproc_000c 30 113))
					(else (localproc_000c 30 114))
				)
				(User canControl: 1)
			)
			(12
				(localproc_000c 30 115)
				(waiter setMotion: MoveTo 103 113 self startUpd:)
				(= local35 0)
				(= local26 0)
				(User canInput: 0)
			)
			(13
				(waiter setMotion: MoveTo 103 104 self)
			)
			(14
				(waiter setPri: 6 setMotion: MoveTo 125 102 self)
			)
			(15
				(waiter stopUpd:)
				(= local29 1)
				(User canInput: 1)
			)
			(16
				(localproc_000c 30 116 25 3)
				(HandsOff)
				(waiter
					view: 44
					setLoop: 5
					setMotion: MoveTo 103 104 self
					startUpd:
				)
				(= local30 1)
			)
			(17
				(waiter
					setLoop: 6
					setPri: 8
					setMotion: MoveTo 103 113 self
				)
			)
			(18
				(waiter setLoop: 4 setMotion: MoveTo 190 113 self)
			)
			(19
				(waiter setLoop: 7 setMotion: MoveTo 190 112 self)
			)
			(20
				(waiter stopUpd:)
				(= local31 1)
				(localproc_000c 30 117 25 3)
				(waiter
					view: 44
					loop: 1
					cel: 0
					posn: 200 113
					setMotion: MoveTo 100 113 self
					startUpd:
				)
				(= local30 0)
				(HandsOn)
			)
			(21
				(waiter
					setLoop: 3
					setPri: 6
					setMotion: MoveTo 100 104 self
				)
			)
			(22
				(waiter setLoop: 0 setMotion: MoveTo 125 102 self)
			)
			(23 (waiter stopUpd:))
			(24
				(ego stopUpd:)
				(HandsOff)
				(= local39 1)
				(localproc_000c 30 118)
				(self cue:)
			)
			(25
				(waiter
					view: 28
					ignoreActors: 1
					illegalBits: 0
					setCycle: Walk
					setStep: 3 2
					setLoop: 1
					setMotion: MoveTo 103 104 self
					startUpd:
				)
			)
			(26
				(waiter setLoop: 2 setMotion: MoveTo 103 125 self)
			)
			(27
				(localproc_000c 30 119)
				(self cue:)
			)
			(28
				(User canInput: 1)
				(if (or local31 local37)
					(if (> dollars local40)
						(localproc_000c 30 120)
						(= dollars (- dollars local40))
						(= local39 1)
						(SolvePuzzle 2)
					else
						(localproc_000c 30 121)
						(localproc_000c 30 122)
					)
				else
					(localproc_000c 30 79)
				)
				(= dateState 3)
				(curRoom newRoom: 105)
			)
		)
	)
)

(instance marieScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (newProp_6 stopUpd:))
			(1
				(HandsOff)
				(= local36 1)
				(SolvePuzzle 1)
				(newProp_6
					loop: 8
					cel: 0
					setCycle: Forward
					cycleSpeed: 3
					startUpd:
				)
				(newProp
					loop: 9
					cel: 0
					setCycle: Forward
					cycleSpeed: 0
					startUpd:
				)
				(= seconds 3)
			)
			(2
				(localproc_000c 30 123 25 3)
				(= seconds 6)
			)
			(3
				(newProp_6 setCel: 0 stopUpd:)
				(newProp setCel: 0 stopUpd:)
				(HandsOn)
				(= local37 1)
				(= local36 0)
				(= local31 0)
			)
			(4
				(HandsOff)
				(newProp_6 loop: 5 cel: 0 setCycle: EndLoop cycleSpeed: 0)
				(newProp posn: 209 111 loop: 6 cel: 0 setCycle: EndLoop)
				(= local45 (+ local45 1))
				(newProp_3
					view: 285
					loop: 0
					cel: 0
					posn: 196 78
					setPri: 7
					init:
					setCycle: Forward
					startUpd:
				)
				(newProp_4
					view: 285
					loop: 1
					cel: 0
					posn: 218 70
					setPri: 7
					init:
					setCycle: Forward
					startUpd:
				)
				(newProp_5
					view: 285
					loop: 2
					cel: 0
					posn: 175 62
					setPri: 7
					init:
					setCycle: Forward
					startUpd:
				)
				(= seconds 4)
			)
			(5
				(newProp_6 setCycle: BegLoop)
				(newProp setCycle: BegLoop)
				(newProp_3 posn: 0 0 stopUpd:)
				(newProp_4 posn: 0 0 stopUpd:)
				(newProp_5 posn: 0 0 stopUpd:)
				(= seconds 4)
				(cond 
					((== local45 2) (SolvePuzzle 2) (localproc_000c 30 124))
					((== local45 3) (localproc_000c 30 125))
					((> local45 3) (localproc_000c 30 126) (self changeState: 7))
				)
				(= seconds 1)
			)
			(6
				(HandsOn)
				(if (== local45 1)
					(SolvePuzzle 2)
					(localproc_000c 30 127)
					(localproc_000c 30 128)
				)
			)
			(7
				(if (> dollars local40)
					(= dateState 4)
				else
					(= dateState 2)
				)
				(curRoom newRoom: 105)
			)
		)
	)
)

(instance sonnyScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canControl: 0)
				(ego posn: -100 0 stopUpd:)
				(newProp
					view: 266
					posn: 209 111
					loop: 6
					cel: 0
					ignoreActors:
					init:
					stopUpd:
				)
				(if (not local25)
					(= local25 1)
					(= local26 1)
					(= local13 100)
				)
				(if (not local23) (self cue:))
			)
			(1
				(= local23 1)
				(localproc_000c 30 129 83)
				(localproc_000c 30 130 83)
				(localproc_000c 30 131 83)
			)
			(4
				(cond 
					(local28
						(ego loop: 1 cel: 3 posn: 214 112)
						(newProp posn: -100 0 stopUpd:)
						(self cue:)
					)
					(local35 (localproc_000c 30 132))
					(else (localproc_000c 30 133))
				)
			)
			(5
				(localproc_000c 30 134 83)
				(= local33 1)
				(User canControl: 1)
			)
			(2
				(= local27 1)
				(SolvePuzzle 3)
				(if (ego has: 11)
					(newProp_2
						view: 266
						posn: 187 89
						loop: 4
						setPri: 7
						init:
						stopUpd:
					)
					(switch ((inventory at: 11) cel?)
						(0 (newProp_2 cel: 6))
						(1 (newProp_2 cel: 3))
						(2 (newProp_2 cel: 5))
					)
					(ego put: 11 30)
				)
				(self cue:)
			)
			(3
				(Print 30 135 #at 69 140 #time 5 #draw)
			)
		)
	)
)

(instance day2Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= local18 1500))
			(1
				(cond 
					(
					(and (== local45 0) (not local28) (not local25)) (= dateState 1) (curRoom newRoom: 105))
					((and (== local45 0) (not local28) local25) (localproc_000c 30 136 25 10) (day2Script changeState: 0))
					((and (== local45 0) local28) (localproc_000c 30 137 25 10) (day2Script changeState: 0))
					((and (> local45 0) (not local28)) (localproc_000c 30 136 25 10) (day2Script changeState: 0))
					((not local39) (= dateState 2) (curRoom newRoom: 105))
					(else (= dateState 3) (curRoom newRoom: 105))
				)
			)
		)
	)
)
