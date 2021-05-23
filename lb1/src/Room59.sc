;;; Sierra Script 1.0 - (do not remove this comment)
(script# 59)
(include game.sh)
(use Main)
(use Intrface)
(use RFeature)
(use Avoider)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	Room59 0
)
(synonyms
	(get detach)
)

(local
	egoSitting
	askedForCarrot
)
(instance Room59 of Room
	(properties
		picture 59
	)
	
	(method (init)
		(super init:)
		(addToPics
			add:
				stove
				dresser
				mirror
				portrait
				shelf
				herb1
				herb2
				table
				herb3
				chair1
				chair2
				pillow
			eachElementDo: #init
			doit:
		)
		(Load SCRIPT AVOIDER)
		(Load VIEW 2)
		(if (== ((inventory at: iCarrot) owner?) 59)
			(carrot setPri: 8 init: stopUpd:)
		)
		(self
			setFeatures:
				chair2
				table
				chair1
				stove
				dresser
				mirror
				portrait
				shelf
				herb1
				herb2
				herb3
				pillow
				Window1
				Window2
				Rug
				Bed
		)
		(if howFast
			(boil loop: 7 setPri: 8 setCycle: Forward init:)
			(fire setPri: 9 setCycle: Forward init:)
		else
			(fire setPri: 9 init: stopUpd:)
			(boil loop: 7 setPri: 8 init: stopUpd:)
		)
		(= gDoor boil)
		(if (and (>= currentAct 2) (<= currentAct 5))
			(self setRegions: 262)
		)
		(LoadMany 143 243)
		(Load VIEW 901)
		(= global208 2)
		(switch currentAct
			(2
				(LoadMany 143 283)
				(= [global377 1] 283)
			)
			(3
				(LoadMany 143 283)
				(= [global377 1] 283)
			)
			(4
				(LoadMany 143 299)
				(= [global377 1] 299)
			)
		)
		(if (== currentAct 5)
			(|= global118 $0001)
		)
		(ego view: 0 posn: 115 160 init:)
		(HandsOn)
	)
	
	(method (doit)
		(if (FirstEntry)
			(Print 59 0)
		)
		(if (& (ego onControl: FALSE) cBLUE)
			(curRoom newRoom: 6)
		)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript AVOIDER)
		(= enteredCelieHouse TRUE)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(if
					(and
						global208
						(Said 'ask,tell,hold,deliver,examine,get,kill,kiss,embrace,flirt>')
					)
					(DisposeScript SAVE)
					(self setScript: (ScriptID 243 0))
					((self script?) handleEvent: event)
					(if (event claimed?)
						(return (event claimed?))
					)
				)
				(cond 
					((Said 'examine>')
						(cond 
							((Said '[<around,at][/room]')
								(Print 59 0)
							)
							((Said '/painting')
								(Print 59 1)
							)
							((Said '/wall')
								(Print 59 2)
							)
							((Said '/curtain')
								(Print 59 3)
							)
							((or (Said '/ceiling') (Said '<up'))
								(Print 59 4)
							)
							((or (Said '/dirt') (Said '<down'))
								(Print 59 5)
							)
						)
					)
					((Said 'get/painting')
						(Print 59 6)
					)
					((or (Said 'get/gumbo') (Said 'eat[/gumbo,food]'))
						(Print 59 7)
					)
					((Said 'sit[/down,*]')
						(if (ego inRect: 180 145 222 185)
							(if (not egoSitting)
								(= egoSitting TRUE)
								(HandsOff)
								(ego setScript: sitDown)
							else
								(Print 59 8)
							)
						else
							(NotClose)
						)
					)
					((Said 'stand[/up,*]')
						(if egoSitting
							(= egoSitting FALSE)
							(HandsOff)
							(ego setScript: standUp)
						else
							(Print 59 9)
						)
					)
					(
						(or
							(Said 'go/bed,sleep')
							(Said 'sleep')
							(Said 'lay<down')
							(Said 'lay/bed<down')
						)
						(Print 59 10)
					)
				)
			else
				FALSE
			)
		)
	)
	
	(method (newRoom n)
		(super newRoom: n)
	)
)

(instance sitDown of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setAvoider: (Avoider new:)
					setMotion: MoveTo 202 165 self
				)
			)
			(1
				(ego view: 2 setPri: 13 loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(2
				(User canInput: TRUE)
				(client setScript: 0)
			)
		)
	)
)

(instance standUp of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setAvoider: 0 setPri: -1 setCycle: BegLoop self)
			)
			(1
				(ego view: 0 loop: 3 setCycle: Walk)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance stove of RPicView
	(properties
		y 127
		x 212
		view 159
		priority 8
		signal ignrAct
	)
	
	(method (handleEvent event)
		(cond 
			((or (MousedOn self event shiftDown) (Said 'examine/oven'))
				(event claimed: TRUE)
				(Print 59 11)
			)
			((Said 'examine/log,oak')
				(Print 59 12)
			)
		)
	)
)

(instance dresser of RPicView
	(properties
		y 152
		x 57
		view 159
		loop 1
		priority 11
		signal ignrAct
	)
	
	(method (handleEvent event)
		(cond 
			((Said '(examine<in),search,open/dresser,drawer')
				(Print 59 13)
			)
			((or (MousedOn self event shiftDown) (Said 'examine/dresser'))
				(event claimed: TRUE)
				(Print 59 14)
			)
		)
	)
)

(instance pillow of RPicView
	(properties
		y 102
		x 160
		view 159
		loop 2
		priority 6
		signal ignrAct
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'examine<below/pillow')
				(Print 59 15)
			)
			((or (MousedOn self event shiftDown) (Said 'examine/pillow'))
				(event claimed: TRUE)
				(Print 59 16)
			)
		)
	)
)

(instance mirror of RPicView
	(properties
		y 106
		x 49
		view 159
		loop 1
		cel 1
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'get/mirror')
				(Print 59 17)
			)
			(
				(or
					(Said 'examine<in/mirror')
					(Said 'examine/reflection')
				)
				(if (< (ego distanceTo: mirror) 50)
					(= theTalker talkLAURA)
					(Say 0 59 18)
				else
					(NotClose)
				)
			)
			((or (MousedOn self event shiftDown) (Said 'examine/mirror'))
				(event claimed: TRUE)
				(Print 59 19)
			)
		)
	)
)

(instance portrait of RPicView
	(properties
		y 99
		x 79
		view 159
		loop 1
		cel 2
	)
)

(instance shelf of RPicView
	(properties
		y 96
		x 255
		view 159
		loop 4
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'get/bottle')
				(Print 59 20)
			)
			((Said 'examine/bottle')
				(Print 59 21)
			)
			((or (MousedOn self event shiftDown) (Said 'examine/shelf'))
				(event claimed: TRUE)
				(Print 59 22)
			)
		)
	)
)

(instance herb1 of RPicView
	(properties
		y 100
		x 231
		view 159
		loop 2
		cel 3
	)
)

(instance herb2 of RPicView
	(properties
		y 104
		x 247
		view 159
		loop 2
		cel 4
	)
)

(instance herb3 of RPicView
	(properties
		y 107
		x 268
		view 159
		loop 2
		cel 5
	)
)

(instance table of RPicView
	(properties
		y 137
		x 257
		view 159
		loop 1
		cel 3
		priority 8
		signal ignrAct
	)
	
	(method (handleEvent event)
		(if (or (MousedOn self event shiftDown) (Said 'examine/nightstand'))
			(event claimed: TRUE)
			(if (== ((inventory at: iCarrot) owner?) 59)
				(Print 59 23)
			else
				(Print 59 24)
			)
		)
	)
)

(instance chair1 of RPicView
	(properties
		y 140
		x 224
		view 159
		cel 2
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(Print 59 25)
		)
	)
)

(instance chair2 of RPicView
	(properties
		y 172
		x 194
		view 159
		cel 1
	)
	
	(method (handleEvent event)
		(if (or (MousedOn self event shiftDown) (Said 'examine/chair'))
			(event claimed: TRUE)
			(Print 59 25)
		)
	)
)

(instance fire of Prop
	(properties
		y 111
		x 206
		view 159
		loop 3
	)
)

(instance carrot of Prop
	(properties
		y 105
		x 242
		view 159
		loop 2
		cel 6
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'ask[/celie]/carrot<for')
				(= theTalker talkCELIE)
				(Say 1 59 26)
				(++ askedForCarrot)
			)
			((Said 'get/carrot')
				(if (< (ego distanceTo: carrot) 40)
					(if (not askedForCarrot)
						(Print 59 27)
					else
						(Ok)
					)
					(carrot dispose:)
					(= gotItem TRUE)
					(ego get: iCarrot)
				else
					(NotClose)
				)
			)
			((or (MousedOn self event shiftDown) (Said 'examine/carrot'))
				(event claimed: TRUE)
				(Print 59 23)
			)
		)
	)
)

(instance boil of Prop
	(properties
		y 94
		x 205
		view 159
		loop 7
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'get/caldron,gumbo')
				(Print 59 7)
			)
			((Said 'get/caldron')
				(Print 59 7)
			)
			(
				(or
					(MousedOn self event shiftDown)
					(Said 'examine/caldron,pan,gumbo')
				)
				(event claimed: TRUE)
				(Print 59 28)
			)
		)
	)
)

(instance Window1 of RFeature
	(properties
		nsTop 58
		nsLeft 99
		nsBottom 85
		nsRight 133
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'open/window')
				(Print 59 29)
			)
			((Said 'break/window')
				(Print 59 30)
			)
			((Said 'examine<(out,through)/window')
				(Print 59 31)
			)
			((or (MousedOn self event shiftDown) (Said 'examine/window'))
				(event claimed: TRUE)
				(Print 59 32)
			)
		)
	)
)

(instance Window2 of RFeature
	(properties
		nsTop 49
		nsLeft 15
		nsBottom 81
		nsRight 190
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(Print 59 32)
		)
	)
)

(instance Rug of RFeature
	(properties
		nsTop 147
		nsLeft 109
		nsBottom 162
		nsRight 160
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'move,(examine<below)/carpet')
				(Print 59 33)
			)
			((or (MousedOn self event shiftDown) (Said 'examine/carpet'))
				(event claimed: TRUE)
				(Print 59 34)
			)
		)
	)
)

(instance Bed of RFeature
	(properties
		nsTop 106
		nsLeft 96
		nsBottom 132
		nsRight 148
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'examine<below/bed')
				(Print 59 35)
			)
			((or (MousedOn self event shiftDown) (Said 'examine/bed'))
				(event claimed: TRUE)
				(Print 59 36)
			)
		)
	)
)
