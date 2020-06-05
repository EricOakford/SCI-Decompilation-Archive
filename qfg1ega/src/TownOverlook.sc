;;; Sierra Script 1.0 - (do not remove this comment)
(script# 65)
(include game.sh)
(use Main)
(use LoadMany)
(use RFeature)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm65 0
)

(local
	local0
	canAskBruno
	local2
	silversWanted =  10
	local4
	whinerMessage
	chatBruno
	local7
	local8
	usedMagic
)
(instance rm65 of Room
	(properties
		picture 65
		style DISSOLVE
		horizon 64
		north 54
		east 66
		south 74
		west 300
	)
	
	(method (init)
		(LoadMany VIEW vBruno vTownGate)
		(super init:)
		(mouseDownHandler add: self)
		(if Night
			(addToPics add: townGate)
			(self setFeatures: onTownGate)
		)
		(addToPics add: townSign eachElementDo: #init doit:)
		(self setFeatures: onTownSign)
		(if
			(and
				(or (== timeODay TIME_MIDDAY) (== timeODay TIME_MIDAFTERNOON))
				(Btst EXITED_TOWN_2)
				(or (not (Btst BEAR_GONE)) (Btst SPIED_THIEVES))
			)
			(Load VIEW vEgoDefeatedMagic)
			(bruno init: setScript: brunoFlippingDagger)
			(keyDownHandler add: bruno)
		)
		(StatusLine enable:)
		(NormalEgo)
		(ego init:)
		(if Night (ego illegalBits: (| cWHITE cLGREY)))
		(switch prevRoomNum
			(74
				(ego posn: 160 188 setMotion: MoveTo 160 170)
			)
			(54
				(ego posn: 160 66 setMotion: MoveTo 160 75)
			)
			(66
				(ego posn: 318 140 setMotion: MoveTo 265 140)
			)
			(else 
				(if (not (Btst EXITED_TOWN))
					(Bset EXITED_TOWN)
					(ego setScript: firstEntrance)
				else
					(ego posn: 10 135 setMotion: MoveTo 30 135)
					(Bset EXITED_TOWN_2)
				)
			)
		)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(if chatBruno (SolvePuzzle POINTS_RECEIVEBADADVICEFROMBRUNO 2)
			(HighPrint 65 0)
			;"If ya want a good time, try the 'Dragon's Breath' at the bar."
			)
		(Bset VISITED_TOWNOVERLOOK)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(cond 
					((super handleEvent: event))
					((Said 'open/gate,door')
						(if Night
							(HighPrint 65 1)
							;The town gate is locked for the night.
							else
							(HighPrint 65 2)
							;The gate is open.
							)
						)
					((Said 'close/gate,door')
						(if Night
							(HighPrint 65 3)
							;The town gate is closed.
							else
							(HighPrint 65 4)
							;The gate is kept open during the day.
							)
						)
					((Said 'climb,climb[/gate,wall]')
						(if Night
							(if (and (< (ego x?) 114) (< (ego y?) 120))
								(if (TrySkill CLIMB tryClimbIntoTown 0)
									(HighPrint 65 5)
									;After making sure nobody is watching, you climb over the town's wall.
									(curRoom newRoom: 300)
								else
									(HighPrint 65 6)
									;Climbing the wall proves too difficult for your current level of skill, but keep practicing.
								)
							else
								(HighPrint 65 7)
								;You're not in a good spot for climbing the wall.
							)
						else
							(HighPrint 65 8)
							;The town gate is wide open.  You would look silly climbing the wall.
						)
					)
					(
					(or (Said 'look<down') (Said 'look[/road,ground,!*]'))
					(HighPrint 65 9)
					;The road from town branches here.  The eastern fork is the road which brought you into this valley.
					)
					((Said 'look>')
						(cond 
							((or (Said '[<at,around]/wall') (Said '/west'))
								(HighPrint 65 10)
								;The town wall is made of very sturdy carved rock.  The sign has been carved from wood.
								)
							((or (Said '<up') (Said '/sky'))
								(if Night
									(HighPrint 65 11)
									;The full moon portends dangerous times.
								else
									(HighPrint 65 12)
									;What can be seen of the sky through the trees is bright and clear.
								)
							)
							((Said '/tree')
								(HighPrint 65 13)
								;You can see pines, cedars, birches and other trees frequently associated with mountain forests.
								)
							((Said '/south')
								(HighPrint 65 14)
								;Heavy forest looms to the south.
								)
							((Said '/east')
								(HighPrint 65 15)
								;The main road by which you entered this valley winds its way through the dense forest.
								)
							((Said '/north')
								(HighPrint 65 16)
								;The north road winds its way through the woods.
								)
						)
					)
				)
			)
		)
	)
)

(instance firstEntrance of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(cSound number: 22 loop: 1 priority: 0 play:)
				(ego posn: 90 140 setMotion: MoveTo 100 140 self)
			)
			(1
				(HandsOn)
				(SolvePuzzle POINTS_LEAVETOWNFIRSTTIME 1)
				(HighPrint 65 17)
				;The breeze is cool, but you feel a shiver deeper than just the cold.  You are on your own in a very dangerous place.
			)
		)
	)
)

(instance bruno of Actor
	(properties
		y 109
		x 37
		view vBruno
		cycleSpeed 2
	)
	
	(method (doit)
		(cond 
			((== script knifeEgo))
			(
				(and
					local4
					(or
						(and
							(<= 162 (ego x?))
							(<= (ego x?) 223)
							(<= 67 (ego y?))
							(<= (ego y?) 84)
						)
						(and
							(<= 100 (ego x?))
							(<= (ego x?) 203)
							(<= 84 (ego y?))
							(<= (ego y?) 155)
						)
					)
				)
				(self setScript: knifeEgo)
			)
			(
				(and
					local4
					(< (ego x?) 100)
					(> (ego y?) 84)
					(!= whiner script)
				)
				(self setScript: whiner)
			)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((== (event type?) mouseDown)
				(if (MouseClaimed bruno event shiftDown)
					(HighPrint 65 18)
					;Bruno is big and a tough fighter.  He's also very adept at throwing daggers.
				)
			)
			((== (event type?) saidEvent)
				(cond 
					(
					(Said 'look[<at,around,up][/area,scenery,cliff,north]')
					(HighPrint 65 19)
					;Next to the town's wall, you notice a man who appears to be waiting for someone.
					)
					((Said 'look/man,bruno,bandit,thief')
						(HighPrint 65 20)
						;Bruno hangs around the town gates.  He will sell information.
						)
					((Said 'look/dagger')
						(HighPrint 65 21)
						;It looks like a particularly deadly weapon, and the thief handles it with great skill.
						)
					((Said 'display,made/sign[<thief]')
						(HighPrint 65 22)
						;Bruno nods and makes the countersign.
						;"Ya need ta talk to Sneak about the password.  Ya can find him in the alley at night."
						)
					(
						(or
							(Said 'get,fight,kill,beat,beat[/bruno,man,thief,bandit]')
							(Said 'grab,get/dagger')
						)
						(if
							(or
								(and
									(<= 162 (ego x?))
									(<= (ego x?) 223)
									(<= 67 (ego y?))
									(<= (ego y?) 84)
								)
								(and
									(<= 100 (ego x?))
									(<= (ego x?) 203)
									(<= 84 (ego y?))
									(<= (ego y?) 155)
								)
							)
							(bruno setScript: knifeEgo)
						else
							(if (and (< (ego x?) 100) (> (ego y?) 84))
								(HighPrint 65 23)
								;"Get outta my face."
							)
							(= local4 1)
						)
					)
					((Said 'throw') (bruno setScript: knifeEgo))
					((Said 'cast') (= usedMagic TRUE) (bruno setScript: knifeEgo))
					((and local7 (Said 'ask'))
						(if (Random 0 1)
							(HighPrint 65 24)
							;"Give me dough- ya know."
						else
							(HighPrint 65 25)
							;"A little silver might loosen my lip."
							(= silversWanted 1)
						)
					)
					((Said 'buy/info')
						(HighPrint 65 26)
						;You had better talk with him and find out how much he wants for his information.
						)
					((and (not local8) (Said 'ask')) (= local7 1)
						(HighPrint 65 27)
						;"What's in it for me?  If ya give me a silver, I might have a bit of info you can use."
						)
					(
						(and
							local7
							(or (Said 'pay/man,bruno,bandit') (Said 'pay[/!*]'))
						)
						(if (GiveMoney 1)
							(= local7 0)
							(= local8 1)
							(HighPrint 65 28)
							;"For another silver, I can tell ya about where to go to find the Baron, Baba Yaga,
							;or some goblins who have a lot of treasure. So if ya want some information, hand me some cash."
							(= silversWanted 1)
						else
							(HighPrint 65 29)
							;Don't mess around with Bruno.  You don't have a silver.
						)
					)
					(
						(or
							(Said 'pay,gave/gold[<!*]')
							(Said 'pay,gave/alm<gold[<!*]')
						)
						(HighPrint 65 30)
						;(Specify how many gold coins to give him.)
					)
					(
						(or
							(Said 'pay,gave/silver')
							(Said 'pay,gave/alm<silver')
						)
						(if (GiveMoney 1)
							(if (not local8)
								(= local7 0)
								(= local8 1)
								(HighPrint 65 28)
								;"For another silver, I can tell ya about where to go to find the Baron,
								;Baba Yaga, or some goblins who have a lot of treasure. So if ya want some information, hand me some cash."
								(= silversWanted 1)
							else
								(= local0 1)
								(HighPrint 65 31)
								;"Okay, whaddaya wanna know"
							)
						else
							(HighPrint 65 29)
						)
					)
					(
						(or
							(Said 'pay,gave/gold<10')
							(Said 'pay,gave/alm<gold<10')
						)
						(if (GiveMoney 100)
							(if (= silversWanted 100)
								(= chatBruno TRUE)
								(= local2 1)
								(HighPrint 65 32)
								;"Go south and you'll see a hollow log.  Then head west until ya can go south again.
								;Then go east until you can go south again.  From there go west. Them brigands just love vistors."
							else
								(HighPrint 65 33)
								;"What's this for?"
								(HighPrint 65 34)
								;"Don't get me wrong.  I'll take it!"
							)
						else
							(HighPrint 65 35)
							;You don't have enough money.
						)
					)
					(
						(or
							(Said 'pay,gave/gold<2')
							(Said 'pay,gave/alm<gold<2')
						)
						(if (GiveMoney 20)
							(if (== silversWanted 20)
								(= chatBruno TRUE)
								(HighPrint 65 36)
								;"I seen her one time yelling out to the house, 'Hut of brown, now sit down.'
								;That's one spooky dame, let me tell you."
							else
								(HighPrint 65 33)
								;"What's this for?"
								(HighPrint 65 34)
								;"Don't get me wrong.  I'll take it!"
							)
							(HighPrint 65 37)
							;"For ten golds, I'll tell ya where to find some of the robbers what have been robbing people coming into the valley."
							(= silversWanted 100)
						else
							(HighPrint 65 35)
							;You don't have enough money.
						)
					)
					(
					(or (Said 'pay,gave/gold') (Said 'pay,gave/alm<gold'))
						(if (GiveMoney 10)
							(= canAskBruno TRUE)
							(if (== silversWanted 10)
								(HighPrint 65 38)
								;"They hide it in the graveyard.  It's under one of the tombstones.  You can see the thieves if you go there at night."
							else
								(HighPrint 65 33)
								;"What's this for?"
								(HighPrint 65 34)
								;"Don't get me wrong.  I'll take it!"
							)
						else
							(HighPrint 65 35)
							;You don't have enough money.
						)
					)
					(
					(or (Said 'pay/man,bruno,bandit') (Said 'pay[/!*]'))
						(switch silversWanted
							(1
								(if (GiveMoney 1)
									(= local0 1)
									(HighPrint 65 39)
									;"Okay, whaddaya wanna know?"
								else
									(HighPrint 65 29)
									;Don't mess around with Bruno.  You don't have a silver.
								)
							)
							(10
								(if (GiveMoney 10)
									(if (= canAskBruno TRUE) (= canAskBruno FALSE)
										(HighPrint 65 38)
										;"They hide it in the graveyard.
										;It's under one of the tombstones.  You can see the thieves if you go there at night."
										)
									(HighPrint 65 40)
									;"For ten golds, I'll even tell ya where to find some of the robbers what have been robbing people coming into the valley."
									(= silversWanted 100)
								else
									(HighPrint 65 35)
									;You don't have enough money.
									(= silversWanted 1)
								)
							)
							(20
								(if (GiveMoney 20)
									(= chatBruno TRUE)
									(HighPrint 65 36)
									;"I seen her one time yelling out to the house, 'Hut of brown, now sit down.'
									;That's one spooky dame, let me tell you."
									(HighPrint 65 37)
									;"For ten golds, I'll tell ya where to find some of the robbers what have been robbing people coming into the valley."
									(= silversWanted 100)
								else
									(= silversWanted 1)
									(HighPrint 65 35)
									;You don't have enough money.
								)
							)
							(100
								(if (GiveMoney 100)
									(= chatBruno TRUE)
									(= local2 1)
									(HighPrint 65 41)
									;"Go south and you'll see a hollow log.  Then head west until ya can go south again.
									;Then go east until you can go south again.  From there, go west.  Them brigands just love visitors."
								else
									(HighPrint 65 35)
									;You don't have enough money.
									(= silversWanted 1)
								)
							)
						)
					)
					((Said 'chat,ask//hut,house,chicken,rhyme')
						(HighPrint 65 42)
						;"For two gold, I'll tell ya how to get into old Baba Yaga's hut."
						(= silversWanted 20))
					((Said 'chat,ask//leader')
						(HighPrint 65 43)
						;"Nobody knows much about the leader."
						)
					((Said 'chat,ask//bandit,(lair<bandit)')
						(HighPrint 65 44)
						;"For ten gold, I'll tell ya where some of those robbers are that're robbin' folks as they come inta the valley."
						(= silversWanted 100))
					((Said 'chat,ask>')
						(if local0
							(= local0 0)
							(cond 
								((Said '//name,handle,thief,bruno') (= chatBruno 1)
									(HighPrint 65 45)
									;"Just call me Bruno.  That was an easy silver."
									)
								((Said '//baba,baba,ogress,baba')
									(= chatBruno TRUE)
									(HighPrint 65 46)
									;"She's an ugly hag who knows some magic.  She lives in a weird house."
									(HighPrint 65 47)
									;"Her place is due west of the Baron's place.  Can't miss it.  And for two gold, I'll tell ya how to get into her hut."
									(= silversWanted 20)
								)
								((Said '//baron') (= chatBruno TRUE)
									(HighPrint 65 48)
									;"Ya just take the road north from here past the Healer's and go till ya get to his castle."
									)
								((Said '//goblin') (= chatBruno TRUE)
									(HighPrint 65 49)
									;"The little pests are just northwest of the cemetery.  They're a bunch of wimps."
									)
								((Said '//cemetery') (= chatBruno TRUE)
									(HighPrint 65 50)
									;"It's due west of town.  Nice place to visit, but I wouldn't want to stay, if ya know what I mean."
									)
								((Said '//breath<dragon')
									(HighPrint 65 51)
									;"Everyone loves Dragon's Breath."
									)
								((Said '//meisterson,otto')
									(HighPrint 65 52)
									;"The Sheriff and the goon are pals.   Otto even lives with the Sheriff and his wife."
									)
								((Said '//bouncer,bouncer')
									(HighPrint 65 53)
									;"He's a pal of mine.  He usually hangs out in the bar.  A real sweet guy.  Give him a slap on the back for me."
									)
								((Said '//password')
									(HighPrint 65 54)
									;"The thieves' password?  Sorry, I can't risk losing my thief license.
									;Chief wouldn't like it if I blabbed that to the likes'a you."
									(= local0 1))
								((Said '//boss')
									(HighPrint 65 55)
									;"He's the only one in the valley who can beat me at daggers."
									)
								((Said '//healer')
									(HighPrint 65 56)
									;"She's got some good potions.  Her hut is just to the north of here."
									)
								((Said '//warlock')
									(HighPrint 65 57)
									;"He's some kinda magic user.  And he laughs alot.  That's all I know."
									)
								((Said '//antwerp')
									(HighPrint 65 58)
									;"Hahahaha!  Yeh, the antwerp is a good monster for you to fight.  It loves to play."
									)
								((Said '//monster')
									(HighPrint 65 59)
									;"Lots'a monsters in this valley.  Most of 'em are wimps, though."
									)
								(else (event claimed: 1) (= local0 1)
									(HighPrint 65 60)
									;"Hey, I just know what I know.  If you want other info, ask someone else."
									)
							)
							(if (and (!= silversWanted 20) (not local0))
								(HighPrint 65 61)
								;"For a gold, well, I can tell where the Thieves' Guild is hidden."
								(= silversWanted 10)
							)
						else
							(event claimed: TRUE)
							(if local8
								(= silversWanted 1)
								(if (Random 0 1)
									(HighPrint 65 24)
									;"Give me dough- ya know."
								else
									(HighPrint 65 25)
									;"A little silver might loosen my lip."
								)
							else
								(HighPrint 65 62)
								;"You talk'n ta me?"
							)
						)
					)
					((Said '/hello')
						(HighPrint 65 62)
						;"You talk'n ta me?"
						)
				)
			)
		)
	)
)

(instance knife1 of Actor
	(properties
		view vBruno
		loop 7
	)
)

(instance knife2 of Actor
	(properties
		view vBruno
		loop 8
	)
)

(instance townGate of PicView
	(properties
		y 115
		x 6
		view vTownGate
		loop 1
	)
)

(instance onTownGate of RFeature
	(properties
		nsTop 23
		nsBottom 116
		nsRight 16
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(MouseClaimed self event shiftDown)
					(Said 'look/gate,fence')
				)
				(HighPrint 65 63)
				;The town gates are closed and barred at night.
			)
		)
	)
)

(instance townSign of PicView
	(properties
		y 64
		x 62
		view vTownGate
	)
)

(instance onTownSign of RFeature
	(properties
		nsTop 35
		nsLeft 12
		nsBottom 68
		nsRight 88
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(MouseClaimed self event shiftDown)
					(Said 'look,read/sign,hamlet,board')
				)
				(HighPrint 65 64)
				;The carved wooden sign reads "SPIELBURG."
			)
		)
	)
)

(instance brunoFlippingDagger of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(bruno setLoop: 4 cel: 0 setCycle: Forward)
				(= seconds (Random 2 6))
			)
			(1
				(bruno setCycle: EndLoop)
				(= seconds (Random 2 6))
			)
			(2 (self changeState: 0))
		)
	)
)

(instance knifeEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(bruno
					setLoop: 5
					cel: 0
					cycleSpeed: 2
					setCycle: CycleTo 4 1 self
				)
			)
			(1
				(knife1
					setLoop: 7
					illegalBits: 0
					ignoreActors:
					ignoreHorizon:
					xStep: 6
					yStep: 7
					init:
					setCycle: Forward
					posn: (+ (bruno x?) 41) (- (bruno y?) 24)
					setMotion: MoveTo (ego x?) (- (ego y?) 22)
				)
				(bruno setCycle: CycleTo 5 1 self)
			)
			(2
				(bruno setCycle: EndLoop)
				(knife2
					setLoop: 8
					illegalBits: 0
					ignoreActors:
					ignoreHorizon:
					xStep: 6
					yStep: 7
					init:
					setCycle: Forward
					posn: (+ (bruno x?) 41) (- (bruno y?) 27)
					setMotion: MoveTo (ego x?) (- (ego y?) 25) self
				)
				(ego setLoop: 1)
			)
			(3
				(knife1 dispose:)
				(knife2 dispose:)
				(ego
					view: vEgoDefeatedMagic
					setLoop: 3
					cycleSpeed: 1
					setMotion: 0
					setCycle: EndLoop self
				)
			)
;			;CI: NOTE: This case already exists, and will probably never be executed
;			(3 (= seconds 5))
			(4
				(if usedMagic
					(EgoDead 65 65
						#icon vEgoClimbing 2 5
						#title {Your figure remains still and silent.}
					)
					;He saw you casting a spell, and he already had a dagger in his hand.
					;It doesn't pay to try to fight someone who uses poisoned daggers.
				else
					(EgoDead 65 66
						#icon vEgoClimbing 2 5
						#title {Your figure remains still and silent.}
					)
					;It takes too long to draw your weapon against someone with a dagger in his hand.
					;It doesn't pay to try to fight someone who uses poisoned daggers.
				)
			)
		)
	)
)

(instance whiner of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(bruno setCycle: EndLoop)
				(= seconds (Random 3 7))
			)
			(1
				(switch whinerMessage
					(0
						(HighPrint 65 67)
						;"Why dontcha leave me alone?  I ain't done nothin'."
						)
					(1
						(HighPrint 65 68)
						;"Hey, I'm just trying to make a buck.  No need to give me a hard time."
						)
					(2
						(HighPrint 65 69)
						;"You look nervous.  Why dontcha take a walk and cool off."
						)
					(3
						(HighPrint 65 70)
						;"Bug off."
						)
					(4
						(HighPrint 65 71)
						;"I think I hear your momma calling you."
						)
					(5
						(HighPrint 65 72)
						;"Your boot's untied."
						)
					(6
						(= whinerMessage -1))
				)
				(++ whinerMessage)
				(self changeState: 0)
			)
		)
	)
)
