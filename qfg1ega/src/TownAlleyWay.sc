;;; Sierra Script 1.0 - (do not remove this comment)
(script# 333)
(include game.sh)
(use Main)
(use RFeature)
(use Motion)
(use Game)
(use Actor)

(public
	rm333 0
)

(local
	nearBeggar
	gaveMoney
	local2
	local3
	chatBeggar
)
(instance beggarBlock of Block
	(properties
		top 151
		left 165
		bottom 172
		right 205
	)
)

(instance beggar of PicView
	(properties
		y 171
		x 178
		view vAlley
	)
)

(instance beggarArm of Prop
	(properties
		y 150
		x 184
		view vAlley
		loop 1
		priority 11
	)
)

(instance frontBarrel of PicView
	(properties
		y 120
		x 119
		view vAlley
		loop 2
	)
)

(instance backBarrel of PicView
	(properties
		y 97
		x 162
		view vAlley
		loop 2
	)
)

(instance rm333 of Room
	(properties
		picture 333
		style HSHUTTER
	)
	
	(method (init)
		(Load VIEW vAlley)
		(super init:)
		(mouseDownHandler add: self)
		(StatusLine enable:)
		(self setLocales: 811 801)
		(NormalEgo)
		(ego
			posn: 120 187
			observeBlocks: beggarBlock
			init:
			setMotion: MoveTo 120 180
		)
		(addToPics
			add: beggar frontBarrel backBarrel
			eachElementDo: #init
			doit:
		)
		(beggarArm ignoreActors: 1 init: stopUpd:)
		(= nearBeggar FALSE)
	)
	
	(method (doit)
		(if (> (ego y?) 187)
			(if (and gaveMoney (not (Btst VISITED_ALLEY_DAY)))
				(cls)
				(HighPrint 333 0)
				;"A word of warning to you -- don't drink the Dragon's Breath!"
			)
			(curRoom newRoom: 330)
		)
		(cond 
			(
			(and (not nearBeggar) (ego inRect: 100 135 215 185))
				(beggarArm setCycle: EndLoop)
				(TimePrint 4 333 1)
				;"Alms?  Alms for the poor?"
				(= nearBeggar 1)
			)
			(
			(and nearBeggar (not (ego inRect: 100 135 215 185))) (beggarArm setCycle: BegLoop) (= nearBeggar 0))
		)
		(super doit:)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(Bset VISITED_ALLEY_DAY)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if
			(or
				(== (event type?) saidEvent)
				(== (event type?) mouseDown)
			)
			(cls)
		)
		(cond 
			((and local2 (Said 'ask>'))
				(= chatBeggar TRUE)
				(cond 
					((> (++ local3) 5)
						(= chatBeggar FALSE)
						(= local2 0)
						(event claimed: TRUE)
						(HighPrint 333 2)
						;"About time I got back to work.  Alms for the poor.  Alms!"
					)
					((Said '//name,beggar')
						(HighPrint 333 3)
						;"My name is Sam.  I've lived here for five years now, and I think I'll be heading on once the pass clears of snow.
						;There's supposed to be some good begging towns to the south."
						)
					((Said '//beg')
						(HighPrint 333 4)
						;"A beggar gets cash only when others get cash, and nobody's got much money here right now."
						)
					((Said '//alm,alm')
						(HighPrint 333 5)
						;"There's honest work up at the Baron's castle, I hear.  But begging's my business."
						)
					((Said '//night,dark')
						(HighPrint 333 6)
						;"I remember when people used to stroll around town at night.  Now, the only people out late are the thieves.
						;And outside of town, man, you don't dare go out there after dark.  The Night Gaunts'll get you for sure."
						)
					((Said '//monster')
						(HighPrint 333 7)
						;"The most dangerous monsters come out at night.  I'd stay inside then if I were you."
						)
					((Said '//gaunt')
						(HighPrint 333 8)
						;"Nobody's ever lived to tell what the Night Gaunts are.  I sure don't want to find out."
						)
					((Said '//bandit,thief,thief')
						(HighPrint 333 9)
						;"Thieves prowl the streets at night.  They don't bother me because I have nothing to steal.
						;The brigands are worse.  They sure hurt business; no one's getting rich but them."
						)
					((Said '//baron,castle,labor,job')
						(HighPrint 333 10)
						;"You can get a job cleaning the Baron's stable, I understand.
						;It's good work if you want to build up your muscles or have a place to spend the night, but you won't get rich.  I'd rather beg, myself."
						)
					(else (= chatBeggar 0)
						(HighPrint 333 11)
						;"I'm afraid I can't tell you much about that."
						(event claimed: 1))
				)
				(if chatBeggar (SolvePuzzle POINTS_TALKTOBEGGAR 1))
			)
			((Said 'get,grab/alm,silver')
				(HighPrint 333 12)
				;Stealing from a beggar is not a heroic action.
				)
			((Said 'read,look/mark')
				(if (<= (ego y?) 130)
					(HighPrint 333 13)
					;Some sharp object appears to have been thrown at these walls several times.
				else
					(HighPrint 333 14)
					;You can't make them out from here.
				)
			)
			((Said 'look[<at,around][/!*,street,alley]')
				(HighPrint 333 15)
				;This grubby place must be an alley.  You feel a strange, cold feeling here.
				;You see some discarded barrels, and a beggar holds out a bowl towards you.
				)
			(
				(or
					(MouseClaimed onBeggar event shiftDown)
					(Said 'look/beggar,man')
				)
				(if gaveMoney (HighPrint 333 16)
					;A thin man with very patched clothing holds out a bowl containing only your kind contribution.
					else
					(HighPrint 333 17)
					;A thin man with very patched clothing holds out an empty bowl.
					)
			)
			(
				(or
					(MouseClaimed onBowl event shiftDown)
					(Said 'look/bowl,basket,hat,item')
				)
				(if gaveMoney
					(HighPrint 333 18)
					;The beggar's bowl contains your donation.
					else
					(HighPrint 333 19)
					;The beggar's bowl looks sadly empty.
					)
			)
			((MouseClaimed onBricks event shiftDown)
				(HighPrint 333 20) ;EO: Question is presented as a statement.
				;Nice place.  How does this guy expect to make a living begging in this alley.
				)
			((MouseClaimed onFirstBarrel event shiftDown)
				(HighPrint 333 21)
				;It's a vessel made of staves, headings and hoops. More commonly known as a barrel.
				)
			((MouseClaimed onSecondBarrel event shiftDown)
				(HighPrint 333 22)
				;This barrel reeks of Troll's Sweat.
				)
			((Said 'look/wall')
				(HighPrint 333 23)
				;The walls seem pretty normal, but there are some curious marks at the north ends of the buildings.
				)
			((Said 'look/barrel')
				(HighPrint 333 24)
				;The barrels are empty, and smell of sour beer.
				)
			((Said 'look/bum')
				(HighPrint 333 25)
				;Although a beggar, the man in the alley appears to be sober.
				)
			((Said 'get,move,grab,(lockpick<up)>')
				(cond 
					((Said '/barrel')
						(HighPrint 333 26)
						;The barrels are too heavy to move.
						)
					((Said '/bowl,hat,basket,item')
						(HighPrint 333 27)
						;You can't.  The beggar is holding onto it with an iron grip.
						)
				)
			)
			((Said 'pay,gave,put,deposit,throw[/alm,silver]')
				(if (GiveMoney 1)
					(SolvePuzzle POINTS_GIVEALMS 1)
					(HighPrint 333 28)
					;"Thanks.  You know, it's really tough trying to make a living begging in this town since the brigands started scaring all the trade away."
					(if (not gaveMoney)
						(HighPrint 333 29)
						;"This used to be such a nice place to live.  My advice to you is not to take up begging.
						;It just doesn't pay.  And don't go out at night."
						)
					(= gaveMoney TRUE)
					(= local2 1)
					(= local3 0)
				else
					(HighPrint 333 30)
					;You reach in your pocket, and suddenly realize that you have no money to give.
				)
			)
			((Said 'ask,chat')
				(HighPrint 333 31)
				;"Silver for the poor."
				)
		)
	)
)

(instance onBricks of RFeature
	(properties
		nsTop 66
		nsLeft 188
		nsBottom 93
		nsRight 201
	)
)

(instance onFirstBarrel of RFeature
	(properties
		nsTop 120
		nsLeft 111
		nsBottom 142
		nsRight 125
	)
)

(instance onSecondBarrel of RFeature
	(properties
		nsTop 97
		nsLeft 154
		nsBottom 121
		nsRight 171
	)
)

(instance onBeggar of RFeature
	(properties
		nsTop 139
		nsLeft 181
		nsBottom 165
		nsRight 194
	)
)

(instance onBowl of RFeature
	(properties
		nsTop 145
		nsLeft 164
		nsBottom 154
		nsRight 175
	)
)
