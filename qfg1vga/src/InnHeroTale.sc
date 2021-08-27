;;; Sierra Script 1.0 - (do not remove this comment)
(script# 301)
(include game.sh) (include "301.shm")
(use Main)
(use Teller)
(use Ware)
(use Procs)
(use Print)
(use Talker)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm301 0
	shameenTalker 1
	shemaTalker 2
	abdullaTalker 3
)

(enum -1	;ware list
	noFunds
	buyNothing
	buyFood
	buyDrink
)

(local
	shameenTellMainBranch = [
		STARTTELL
		C_ROOM
		C_FOOD
		-9		;C_MERCHANT
		-11		;C_NAME
		-38		;C_TOWN
		C_INN
		-15		;C_SHEMA
		ENDTELL
		]
	shameenTellMainBranchMerchant = [
		STARTTELL
		C_ROOM
		C_FOOD
		-33		;C_MERCHANT_HERE
		-11		;C_NAME
		-38		;C_TOWN
		C_INN
		-15		;C_SHEMA
		ENDTELL
		]
	shameenTell1 = [
		STARTTELL
		C_ROBBERY
		C_BRIGANDS
		C_WEALTH
		C_SHAPEIR
		C_CARAVAN
		ENDTELL
		]
	shameenTell2 = [
		STARTTELL
		C_HOMELAND
		C_KATTA
		C_SHAPEIR
		-27		;C_ABDULLA
		ENDTELL
		]
	shameenTell3 = [
		STARTTELL
		C_SPIELBURG
		C_SHERIFF
		C_OTTO
		ENDTELL
		]
	shemaTellMainBranch = [
		STARTTELL
		C_ROOM
		C_FOOD
		C_DRINK
		C_QUESTIONS
		-11		;C_NAME
		-9		;C_MERCHANT
		-13		;C_SHAMEEN
		ENDTELL
		]
	shemaTell1 = [
		STARTTELL
		C_ABDULLA
		C_ROBBERY
		ENDTELL
		]
	shemaTell2 = [
		STARTTELL
		C_HOMELAND
		C_SHAPEIR
		C_KATTA
		C_DANCE
		ENDTELL
		]
	merchantTellMainBranch = [
		STARTTELL
		-12		;C_ROBBERY
		C_NAME
		-5		;C_FRIENDS
		C_MERCHANT
		-3		;C_CARPET
		ENDTELL
		]
	merchantTell1 = [
		STARTTELL
		-2		;C_BRIGANDS
		-16		;C_WEALTH
		ENDTELL
		]
	merchantTell2 = [
		STARTTELL
		C_SHEMA
		C_SHAMEEN
		C_SHAPEIR
		ENDTELL
		]
	merchantTell3 = [
		STARTTELL
		C_MAGIC
		C_MINOTAUR
		C_LEADER
		C_WIZARD
		C_GUARDS
		ENDTELL
		]
	merchantTell4 = [
		STARTTELL
		-3		;C_CARPET
		ENDTELL
		]
	merchantTell5 = [
		STARTTELL
		C_COMMANDS
		ENDTELL
		]
	[shameenTellTree 8]
	[shemaTellTree 7]
	[merchantTellTree 10]
	shameenTellKeys = [
		STARTTELL
		-9		;C_MERCHANT
		-11		;C_NAME
		-27		;C_ABDULLA
		-38		;C_TOWN
		-15		;C_SHEMA
		ENDTELL
		]
	shameenTellKeysMerchant = [
		STARTTELL
		-33		;C_MERCHANT_HERE
		-11		;C_NAME
		-27		;C_ABDULLA
		-38		;C_TOWN
		-15		;C_SHEMA
		ENDTELL
		]
	shemaTellKeys = [
		STARTTELL
		-11		;C_NAME
		-9		;C_MERCHANT
		-13		;C_SHAMEEN
		ENDTELL
		]
	merchantTellKeys = [
		STARTTELL
		-12		;C_ROBBERY
		-2		;C_BRIGANDS
		-5		;C_FRIENDS
		-16		;C_WEALTH
		-3		;C_CARPET
		ENDTELL
		]
	lickCount
	talkedToShameen
	sittingDown
	local139
	hadDrink
	foodOrdered
	drinkOrdered
	local143
	merchantState
	saveSpeed
)
(procedure (Innteractions theNoun theVerb)
	(return
		(switch theVerb
			(V_DO
				(cond 
					((not (Btst fSittingAtInn))
						(messager say: N_ROOM V_DO)
						(return TRUE)
					)
					((or (== foodOrdered mealORDERED) (== drinkOrdered mealORDERED))
						(messager say: N_ROOM V_STAND C_WAITFORSHEMA)
						(return TRUE)
					)
					((== foodOrdered mealATTABLE)
						(messager say: N_ROOM V_STAND C_EATMEALFIRST)
						(return TRUE)
					)
					((== drinkOrdered mealATTABLE)
						(messager say: N_ROOM V_STAND C_DRINK)
						(return TRUE)
					)
					(else
						(ego setScript: egoStands)
						(if (== (shema script?) comingOut)
							(shema setScript: goingIn)
						)
					)
				)
			)
			(V_LOOK
				(messager say: theNoun V_LOOK)
				(return TRUE)
			)
			(else 
				(if (!= theVerb V_WALK)
					(messager say: N_ROOM NULL C_CUEIT)
				)
				(return TRUE)
			)
		)
	)
)

(instance rm301 of Room
	(properties
		noun N_ROOM
		picture 301
		south 300
	)
	
	(method (init)
		(walkHandler add: self)
		(if (or (== timeODay TIME_SUNSET) (== timeODay TIME_NIGHT))
			(if (not (Btst fSawMerchantAtInn))
				(= merchantState TRUE)
			else
				(= merchantState (Random 0 1))
			)
		)
		(if merchantState
			(Bset fMerchantIsAtInn)
			(= [shameenTellTree 0] @shameenTellMainBranchMerchant)
		else
			(= [shameenTellTree 0] @shameenTellMainBranch)
		)
		(= [shameenTellTree 1] @shameenTell1)
		(= [shameenTellTree 2] @shameenTell2)
		(= [shameenTellTree 3] @shameenTell1)
		(= [shameenTellTree 4] @shameenTell3)
		(= [shameenTellTree 5] @shameenTell2)
		(= [shameenTellTree 6] ENDTELL)
		(= [shemaTellTree 0] @shemaTellMainBranch)
		(= [shemaTellTree 1] @shemaTell2)
		(= [shemaTellTree 2] @shemaTell1)
		(= [shemaTellTree 3] @shemaTell2)
		(= [shemaTellTree 4] ENDTELL)
		(= [merchantTellTree 0] @merchantTellMainBranch)
		(= [merchantTellTree 1] @merchantTell1)
		(= [merchantTellTree 2] @merchantTell3)
		(= [merchantTellTree 3] @merchantTell2)
		(= [merchantTellTree 4] @merchantTell4)
		(= [merchantTellTree 5] @merchantTell5)
		(= [merchantTellTree 6] ENDTELL)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						3 162
						3 189
						0 189
						0 0
						319 0
						319 189
						315 189
						313 184
						293 185
						253 145
						219 145
						201 111
						242 111
						224 92
						204 93
						197 89
						85 89
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						65 138
						56 127
						76 110
						145 110
						136 138
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						204 146
						210 174
						180 180
						80 180
						99 146
					yourself:
				)
		)
		(LoadMany VIEW 1301 302 301 303 304)
		(LoadMany SOUND 21 50)
		(rm301 style: (if (== prevRoomNum 302) (| BLACKOUT IRISOUT) else WIPELEFT))
		(super init:)
		(|= disabledActions (| ACTION_RUN ACTION_SNEAK))
		(self
			setFeatures:
				onSleepDoor
				onLargeTable
				onFireplace
				onKitchenDoor
				onPlates
				onPots
				onGenieLamp
				onMask
				onTable
		)
		;UPGRADE
;;;		(onSleepDoor init:)
;;;		(onLargeTable init:)
;;;		(onFireplace init:)
;;;		(onKitchenDoor init:)
;;;		(onPlates init:)
;;;		(onPots init:)
;;;		(onGenieLamp init:)
;;;		(onMask init:)
;;;		(onTable init:)
		
		(self setRegions: TOWN)
		(Bclr fEgoSitting)
		(Bclr fSittingAtInn)
		(Bclr fShemaBringsOrder)
		(Bclr fEatenAtInn)
		(Bclr fShameenStands)
		(Bclr fShemaAsks)
		(Bclr fHaveOrdered)
		(theIconBar enable:)
		(NormalEgo)
		(ego actions: egoActions init: ignoreActors:)
		(ChangeGait MOVE_WALK FALSE)
		(theFire init:)
		(floorReflectLite init:)
		(leftCandle init:)
		(rightCandle init:)
		(tools init:)
		(Chair
			init:
			approachVerbs:
				V_DO V_ACORN V_CANDELABRA V_CANDLESTICKS V_CHEETAURCLAW V_DAGGER
				V_FAIRYDUST V_FLASK V_FLOWERS V_WATER V_FRUIT V_GHOSTOIL
				V_GREENFUR V_HEALING V_BRASSKEY V_LEATHER V_LOCKPICK V_MAGICGEM
				V_MANA V_MANDRAKE V_MAGICMIRROR V_MUSHROOM V_MUSICBOX V_PEARLS
				V_PAPER V_RATIONS V_RING V_ROCK V_SEED V_SHIELD
				V_MONEY V_VIGOR V_SWORD V_THIEFKIT V_THIEFLICENSE V_TROLLBEARD
				V_VASE V_VEGETABLES
			stopUpd:
		)
		(SwDoor init: stopUpd:)
		(if (Btst fMerchantIsAtInn)
			(Bset fSawMerchantAtInn)
			(merchantTeller
				init: merchant @merchantTellMainBranch @merchantTellTree @merchantTellKeys
			)
			(merchant actions: merchantTeller init:)
			(shameenTeller init: shameen @shameenTellMainBranchMerchant @shameenTellTree @shameenTellKeysMerchant)
		else
			(shameenTeller init: shameen @shameenTellMainBranch @shameenTellTree @shameenTellKeys)
		)
		(shameen actions: shameenTeller init:)
		(shemaTeller init: shema @shemaTellMainBranch @shemaTellTree @shemaTellKeys)
		(shema init: actions: shemaTeller)
		(switch prevRoomNum
			(302
				(ego loop: 0 posn: 100 100 setMotion: PolyPath 150 110)
			)
			(else 
				(ego posn: 235 183 setMotion: MoveTo 235 175)
			)
		)
		(cSound number: 21 setLoop: 1 play:)
		(pillow init:)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(if (cast contains: merchant)
			(merchant dispose:)
		)
		(Bclr fMerchantIsAtInn)
		(walkHandler delete: self)
		(= disabledActions NULL)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_SLEEP
				(messager say: N_ROOM V_SLEEP)
			)
			(V_WALK
				(cond 
					((not (Btst fSittingAtInn))
						(super doVerb: theVerb &rest)
					)
					((or (== foodOrdered mealORDERED) (== drinkOrdered mealORDERED))
						(messager say: N_ROOM V_STAND C_WAITFORSHEMA)
					)
					((== foodOrdered mealATTABLE)
						(messager say: N_ROOM V_STAND C_EATMEALFIRST)
					)
					((== drinkOrdered mealATTABLE)
						(messager say: N_ROOM V_STAND C_DRINK)
					)
					(else
						(ego setScript: egoStands)
						(if (== (shema script?) comingOut)
							(shema setScript: goingIn)
						)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance merchant of Actor
	(properties
		x 101
		y 262
		z 100
		noun N_MERCHANT
		view 302
		loop 1
	)
	
	(method (init &tmp temp0)
		(self
			setPri: 14
			ignoreActors:
			signal: fixPriOn
			stopUpd:
		)
		(mLegs addToPic:)
		(mMug addToPic:)
		(super init:)
	)
	
	(method (dispose)
		(mLegs dispose:)
		(mMug dispose:)
		(super dispose:)
	)
)

(instance merchantTeller of Teller
	(method (showDialog &tmp temp0)
		(return
			(if
				(and
					(== (= temp0 (super showDialog: -3 (Btst fGenerousToMerchant))) -16)
					(not (Btst fGenerousToMerchant))
				)
				(return (- temp0))
			else
				(return temp0)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_TALK
				(if (not sittingDown)
					(ego setScript: sitScript)
				else
					(SolvePuzzle f301TalkToAbdulla 5)
					(super doVerb: theVerb &rest)
				)
			)
			(V_RATIONS
				(ego setScript: feedMerchant)
			)
			(V_MONEY
				(cond 
					((or (== merchantState 3) (== merchantState 2))
						(messager say: N_MERCHANT V_MONEY NULL 6)
					)
					((== (shema script?) comingOut)
						((= wareList (List new:))
							add:
								((Clone Ware) name: {Abdulla a meal} price: {4})
								((Clone Ware) name: {Abdulla a drink} price: {2})
						)
						(switch ((ScriptID WARE 0) doit:)
							(noFunds
								(messager say: N_MERCHANT V_MONEY NULL 3)
							)
							(buyNothing
								(messager say: N_MERCHANT V_MONEY C_BUYNOTHING)
							)
							(buyFood
								(SolvePuzzle f301GaveMerchantFood 2)
								(= merchantState 3)
								(merchant setScript: giveThanks)
							)
							(buyDrink
								(= merchantState 2)
								(merchant setScript: giveThanks)
							)
						)
					)
					(else
						(messager say: N_MERCHANT V_MONEY NULL 2)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
		(return TRUE)
	)
)

(instance shema of Actor
	(properties
		noun N_SHEMA
		view 303
	)
	
	(method (init &tmp temp0)
		(super init:)
		(self
			illegalBits: 0
			ignoreActors:
			posn: 500 500
			setCycle: Walk
		)
	)
)

(instance shemaTeller of Teller
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_MONEY
					((= wareList (List new:))
						add:
							((Clone Ware) name: {yourself a meal} price: {3})
							((Clone Ware) name: {yourself a drink} price: {1})
					)
					(switch ((ScriptID WARE 0) doit:)
						(noFunds
							(messager say: N_SHEMA V_MONEY C_NOFUNDS)
						)
						(buyNothing
							(messager say: N_SHEMA V_MONEY C_BUYNOTHING)
						)
						(buyFood
							(SolvePuzzle f301OrderMeal 1)
							(cond 
								((Btst fStarving)
									(messager say: N_SHEMA V_MONEY C_STARVING)
									(Bclr fStarving)
									(Bclr fHungry)
									(= foodOrdered TRUE)
									(if (== (shema script?) comingOut)
										(comingOut changeState: 7)
									)
								)
								((Btst fHungry)
									(messager say: N_SHEMA V_MONEY C_BUYFOOD)
									(Bclr fHungry)
									(= freeMeals 1)
									(= foodOrdered TRUE)
									(if (== (shema script?) comingOut)
										(comingOut changeState: 7)
									)
								)
								(freeMeals
									(messager say: N_SHEMA V_MONEY C_CUEIT)
								)
								(else
									(= freeMeals 2)
									(messager say: N_SHEMA V_MONEY C_BUYFOOD)
									(= foodOrdered TRUE)
									(if (== (shema script?) comingOut)
										(comingOut changeState: 7)
									)
								)
							)
						)
						(buyDrink
							(if hadDrink
								(messager say: N_SHEMA V_MONEY C_NOMORECOFFEE)
							else
								(messager say: N_SHEMA V_MONEY C_ORDERCOFFEE)
								(= drinkOrdered TRUE)
								(if (== (shema script?) comingOut)
									(comingOut changeState: 7)
								)
							)
						)
					)
					(return TRUE)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance shameen of Prop
	(properties
		noun N_SHAMEEN
		view 302
		loop 2
	)
	
	(method (init &tmp temp0)
		(= nightPalette 1302)
		(PalVary PALVARYTARGET 1302)
		(AssertPalette 302)
		(self x: 232 y: 132 ignoreActors:)
		(self setScript: shameensIntro)
		(super init:)
	)
)

(instance shameenTeller of Teller
	(method (showDialog)
		(super
			showDialog:
				-9 (not (Btst fSawMerchantAtInn))
				-33 (Btst fMerchantIsAtInn)
				-27 (Btst fSawMerchantAtInn)
		)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_TALK
					(if (not talkedToShameen)
						(messager say: N_SHAMEEN V_CONVERSATION C_FOOD)
						(++ talkedToShameen)
					else
						(SolvePuzzle f301TalkToShameen 1)
						(super doVerb: theVerb)
					)
					(return TRUE)
				)
				(V_MONEY
					(if (or foodOrdered drinkOrdered)
						(messager say: N_SHAMEEN V_MONEY C_CANTSLEEP 2)
					else
						((= wareList (List new:))
							add: ((Clone Ware) name: {A night's rest} price: {5})
						)
						(switch ((ScriptID WARE 0) doit:)
							(noFunds
								(messager say: N_SHAMEEN V_MONEY C_NOFUNDS)
							)
							(1
								(cond 
									((Btst fWornOut) (Bset fRentedRoom)
										(shameen setScript: rentARoom)
									)
									((and (< 750 Clock) (< Clock 2550))
										(messager say: N_SHAMEEN V_MONEY C_CANTSLEEP 1))
									((Btst fStarving)
										(messager say: N_SHAMEEN V_MONEY C_STARVING)
									)
									(else (Bset fRentedRoom)
										(shameen setScript: rentARoom)
									)
								)
							)
						)
					)
					(return TRUE)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance Food of View
	(properties
		x 160
		y 150
		noun N_FOOD
		view 304
		loop 6
		priority 14
		signal fixPriOn
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_DO
				(ego setScript: eatIt)
			)
			(V_TALK
				(ego setScript: eatIt)
			)
			(V_LOOK
				(if (or (Btst fEatenAtInn) (== foodOrdered mealFINISHED))
					(messager say: N_FOOD V_LOOK C_ORDERCONSUMED)
					(= foodOrdered mealFINISHED)
					(ego setScript: eatIt)
				else
					(messager say: N_FOOD V_LOOK C_ORDERATTABLE)
					(Bset fEatenAtInn)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance movingFood of View
	(properties
		view 304
		loop 4
	)
	
	(method (doit)
		(movingFood
			x: (shema x?)
			y: (shema y?)
			cel: (shema cel?)
			priority: (+ 1 (shema priority?))
		)
		(super doit:)
	)
)

(instance Drink of View
	(properties
		x 161
		y 150
		noun N_DRINK
		view 304
		loop 7
		priority 14
		signal fixPriOn
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(ego setScript: eatIt)
			)
			(V_LOOK
				(if (or (== drinkOrdered mealATTABLE) (== foodOrdered mealATTABLE))
					(messager say: N_DRINK V_LOOK C_ORDERATTABLE)
					(= hadDrink TRUE)
				)
			)
			(else 
				(Prints {in else of Drink})
				(super doVerb: theVerb)
			)
		)
	)
)

(instance mLegs of View
	(properties
		x 103
		y 160
		view 302
		loop 7
		priority 12
		signal fixPriOn
	)
)

(instance tools of Prop
	(properties
		x 265
		y 134
		noun N_TOOLS
		view 301
		loop 4
	)
	
	(method (doVerb theVerb)
		(Innteractions noun theVerb)
	)
)

(instance mMug of View
	(properties
		x 125
		y 157
		noun N_MUG
		view 301
		loop 4
		cel 3
		priority 14
	)
	
	(method (doVerb theVerb)
		(Innteractions noun theVerb)
	)
)

(instance pillow of Prop
	(properties
		x 229
		y 125
		noun N_PILLOW
		view 302
		loop 3
	)
	
	(method (doVerb theVerb)
		(Innteractions noun theVerb)
	)
)

(instance InnMusic of Sound
	(properties
		flags $ffff
		number 21
	)
)

(instance clapSound of Sound
	(properties
		number 118
	)
)

(instance onTable of Feature
	(properties
		x 100
		y 120
		noun N_TABLE
		nsTop 104
		nsLeft 63
		nsBottom 138
		nsRight 138
	)
	
	(method (doVerb theVerb)
		(Innteractions noun theVerb)
	)
)

(instance onKitchenDoor of Feature
	(properties
		x 158
		y 56
		noun N_KITCHENDOOR
		nsTop 21
		nsLeft 134
		nsBottom 90
		nsRight 181
	)
	
	(method (doVerb theVerb)
		(Innteractions noun theVerb)
	)
)

(instance onPlates of Feature
	(properties
		x 241
		y 52
		noun N_PLATES
		nsTop 32
		nsLeft 224
		nsBottom 72
		nsRight 257
	)
	
	(method (doVerb theVerb)
		(Innteractions noun theVerb)
	)
)

(instance onMask of Feature
	(properties
		x 27
		y 71
		noun N_MASK
		nsTop 54
		nsLeft 16
		nsBottom 87
		nsRight 37
	)
	
	(method (doVerb theVerb)
		(Innteractions noun theVerb)
	)
)

(instance onPots of Feature
	(properties
		x 16
		y 112
		noun N_POTS
		nsTop 96
		nsLeft 3
		nsBottom 128
		nsRight 28
	)
	
	(method (doVerb theVerb)
		(Innteractions noun theVerb)
	)
)

(instance onGenieLamp of Feature
	(properties
		x 255
		y 80
		noun N_GENIELAMP
		nsTop 74
		nsLeft 245
		nsBottom 86
		nsRight 264
	)
	
	(method (doVerb theVerb)
		(Innteractions noun theVerb)
	)
)

(instance onFireplace of Feature
	(properties
		x 288
		y 130
		noun N_FIREPLACE
		nsTop 87
		nsLeft 276
		nsBottom 181
		nsRight 319
	)
	
	(method (doVerb theVerb)
		(Innteractions noun theVerb)
	)
)

(instance onLargeTable of Feature
	(properties
		x 134
		y 171
		z 10
		noun N_TABLE
		nsTop 137
		nsLeft 87
		nsBottom 183
		nsRight 181
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_DAGGER
					(messager say: N_ROOM V_DAGGER)
					(return TRUE)
				)
				(V_ROCK
					(messager say: N_ROOM V_DAGGER)
					(return TRUE)
				)
				(V_SWORD
					(messager say: N_ROOM V_DAGGER)
					(return TRUE)
				)
				(V_DO
					(if (or (== foodOrdered mealATTABLE) (== drinkOrdered mealATTABLE))
						(ego setScript: eatIt)
					else
						(messager say: N_TABLE V_DO)
						(return TRUE)
					)
				)
				(V_TALK
					(if (or (== foodOrdered mealATTABLE) (== drinkOrdered mealATTABLE))
						(ego setScript: eatIt)
					else
						(messager say: N_TABLE V_DO C_CUEIT)
						(return TRUE)
					)
				)
				(V_LOOK
					(cond 
						((or (Btst fEatenAtInn) (== foodOrdered mealFINISHED))
							(messager say: N_FOOD V_LOOK C_ORDERCONSUMED)
							(return TRUE)
							(= foodOrdered mealFINISHED)
						)
						((== foodOrdered mealATTABLE)
							(messager say: N_FOOD V_LOOK C_ORDERATTABLE)
							(return TRUE)
							(Bset 302)
						)
						((== drinkOrdered mealATTABLE)
							(messager say: N_DRINK V_LOOK C_ORDERATTABLE)
							(= hadDrink TRUE)
						)
						(else
							(messager say: N_TABLE V_LOOK)
							(return TRUE)
						)
					)
				)
				(else 
					(messager say: N_ROOM NULL C_CUEIT)
					(return TRUE)
				)
			)
		)
	)
)

(instance onSleepDoor of Feature
	(properties
		x 67
		y 66
		noun N_SLEEPDOOR
		nsTop 29
		nsLeft 47
		nsBottom 103
		nsRight 86
	)
	
	(method (doVerb theVerb)
		(Innteractions noun theVerb)
	)
)

(instance shameenTalker of Talker
	(properties
		x 10
		y 10
		view 1301
		talkWidth 260
		textX 15
		textY 110
	)
	
	(method (init)
		(= nightPalette 2301)
		(PalVary PALVARYTARGET 2301)
		(AssertPalette 1301)
		(= font userFont)
		(super init: shameenBust shameenEye shameenMouth &rest)
		(brows init: setCycle: Blink)
	)
	
	(method (dispose)
		(brows dispose:)
		(super dispose:)
	)
)

(instance shemaTalker of Talker
	(properties
		x 10
		y 10
		view 1302
		talkWidth 260
		textX 15
		textY 110
	)
	
	(method (init)
		(= nightPalette 2302)
		(PalVary PALVARYTARGET 2302)
		(AssertPalette 1302)
		(= font userFont)
		(super init: shemaBust shemaEye shemaMouth &rest)
	)
)

(instance abdullaTalker of Talker
	(properties
		x 10
		y 10
		view 1303
		talkWidth 260
		textX 15
		textY 110
	)
	
	(method (init)
		(= nightPalette 2303)
		(PalVary PALVARYTARGET 2303)
		(AssertPalette 1303)
		(= font userFont)
		(super init: abdullaBust abdullaEye abdullaMouth &rest)
	)
)

(instance shemaBust of Prop
	(properties
		view 1302
	)
)

(instance shemaMouth of Prop
	(properties
		nsTop 47
		nsLeft 48
		view 1302
		loop 1
	)
)

(instance shemaEye of Prop
	(properties
		nsTop 33
		nsLeft 40
		view 1302
		loop 2
	)
)

(instance shameenBust of Prop
	(properties
		view 1301
	)
)

(instance shameenMouth of Prop
	(properties
		nsTop 52
		nsLeft 43
		view 1301
		loop 1
	)
)

(instance shameenEye of Prop
	(properties
		nsTop 32
		nsLeft 38
		view 1301
		loop 2
	)
)

(instance brows of Prop
	(properties
		nsTop 30
		nsLeft 39
		view 1301
		loop 3
	)
)

(instance SwDoor of Prop
	(properties
		x 160
		y 23
		noun N_SWINGDOOR
		view 301
		priority 5
		signal (| ignrAct fixPriOn)
		cycleSpeed 12
	)
	
	(method (doVerb theVerb)
		(Innteractions noun theVerb)
	)
)

(instance leftCandle of Prop
	(properties
		x 109
		y 54
		noun N_LEFTCANDLE
		view 301
		loop 1
		cycleSpeed 9
		detailLevel 2
	)
	
	(method (init)
		(self setCycle: Forward)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(Innteractions noun theVerb)
	)
)

(instance rightCandle of Prop
	(properties
		x 207
		y 54
		noun N_RIGHTCANDLE
		view 301
		loop 2
		cycleSpeed 9
		detailLevel 2
	)
	
	(method (init)
		(self setCycle: Forward)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(Innteractions noun theVerb)
	)
)

(instance floorReflectLite of Prop
	(properties
		x 237
		y 133
		noun N_FIREREFLECTION
		view 301
		loop 7
		signal fixPriOn
		cycleSpeed 9
		detailLevel 2
	)
	
	(method (init)
		(self setCycle: RandCycle)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(Innteractions noun theVerb)
	)
)

(instance theFire of Prop
	(properties
		x 281
		y 143
		noun N_FIRE
		view 301
		loop 3
		cycleSpeed 9
		detailLevel 2
	)
	
	(method (init)
		(self setCycle: RandCycle)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(Innteractions noun theVerb)
	)
)

(instance Chair of Prop
	(properties
		x 194
		y 169
		noun N_CHAIR
		approachX 225
		approachY 175
		view 301
		loop 4
		cel 4
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (not (Btst fSittingAtInn))
					(ego setScript: sitScript)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance abdullaBust of Prop
	(properties
		view 1303
	)
)

(instance abdullaMouth of Prop
	(properties
		nsTop 46
		nsLeft 56
		view 1303
		loop 1
	)
)

(instance abdullaEye of Prop
	(properties
		nsTop 33
		nsLeft 53
		view 1303
		loop 2
	)
)

(instance egoActions of Actions
	(method (doVerb theVerb)
		(return
			(if (or (== theVerb V_DAGGER) (== theVerb V_SWORD))
				(messager say: N_MERCHANT V_DAGGER NULL 1)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
)

(instance eatIt of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (cast contains: Food)
					(Food setCel: 5)
				)
				(if (== foodOrdered mealATTABLE)
					(= foodOrdered mealFINISHED)
					(messager say: N_ROOM NULL C_CONSUMEORDER 1 self)
				else
					(self cue:)
				)
			)
			(1
				(if (== drinkOrdered mealATTABLE)
					(= drinkOrdered mealFINISHED)
					(messager say: N_ROOM NULL C_CONSUMEORDER 2 self)
				else
					(self cue:)
				)
			)
			(2
				(shema setScript: comingToClear)
				(self dispose:)
			)
		)
	)
)

(instance feedMerchant of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_MERCHANT V_RATIONS NULL 1 self)
				(SolvePuzzle f301GaveMerchantFood 2)
			)
			(1
				(ego use: iRations 1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance LickIt of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(++ lickCount)
				(shameen
					setLoop: 2
					cel: 0
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(1
				(if (< lickCount (Random 2 5))
					(self changeState: 0)
				else
					(= lickCount 0)
					(shameen setLoop: 4 cel: 0 cycleSpeed: 6 stopUpd:)
					(= seconds (Random 5 15))
				)
			)
			(2
				(self changeState: 0)
			)
		)
	)
)

(instance shameensIntro of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (not (Btst fBeenIn301))
					(shameen setLoop: 4 cel: 0 cycleSpeed: 6)
					(Bset fBeenIn301)
					(= ticks 30)
				else
					(HandsOn)
					(Bclr fShameenStands)
					(shameen setScript: LickIt)
				)
			)
			(1
				(shameen setCycle: EndLoop self)
			)
			(2
				(shameen loop: 6 cel: 0 cycleSpeed: 12 setCycle: EndLoop self)
			)
			(3
				(= ticks 120)
			)
			(4
				(shameen setCycle: BegLoop self)
			)
			(5
				(Bset fShameenStands)
				(shameen stopUpd:)
				(messager say: 17)
				(= ticks 180)
			)
			(6
				(shameen
					setLoop: 4
					cel: 5
					cycleSpeed: 6
					setCycle: BegLoop self
				)
			)
			(7
				(HandsOn)
				(Bclr fShameenStands)
				(shameen setLoop: 2 setScript: LickIt)
			)
		)
	)
)

(instance shameenClaps of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (and (Btst fSawMerchantAtInn) (not (Btst fMetMerchant)))
					(= seconds 2)
				else
					(= ticks 1)
				)
			)
			(1
				(if (and (Btst fSawMerchantAtInn) (not (Btst fMetMerchant)))
					(self changeState: 0)
				else
					(= ticks 1)
				)
			)
			(2
				(if (Btst fShameenStands)
					(self cue:)
				else
					(shameen
						setLoop: 4
						cel: 0
						cycleSpeed: 6
						setCycle: EndLoop self
					)
				)
			)
			(3
				(shameen
					setLoop: 5
					cycleSpeed: 12
					cel: 0
					setCycle: CycleTo 3 1 self
				)
			)
			(4
				(= ticks 40)
			)
			(5
				(shameen cel: 4)
				(= cycles 1)
			)
			(6
				(clapSound play:)
				(= ticks 20)
			)
			(7
				(shameen cel: 3)
				(= ticks 40)
			)
			(8
				(shameen cel: 4)
				(= cycles 1)
			)
			(9
				(clapSound play:)
				(= ticks 20)
			)
			(10
				(shameen cel: 3)
				(= ticks 40)
			)
			(11
				(shameen cel: 4)
				(= cycles 1)
			)
			(12
				(clapSound play:)
				(= ticks 20)
			)
			(13 (= ticks 60))
			(14
				(shema setScript: comingOut)
				(= ticks 1)
			)
			(15
				(shameen setCycle: BegLoop self)
			)
			(16
				(shameen setLoop: 4 cel: 5 setCycle: 0 cycleSpeed: 6)
				(= ticks 20)
			)
			(17
				(shameen setCycle: BegLoop self)
			)
			(18
				(Bclr fShameenStands)
				(shameen stopUpd: setScript: LickIt)
			)
		)
	)
)

(instance emotionalMerchant of Script
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(merchant setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(1
				(merchant setLoop: 0 cel: 0 setCycle: EndLoop self)
			)
			(2
				(merchant setLoop: 0 cel: 2)
				(= ticks 18)
			)
			(3
				(merchant setLoop: 0 cel: 3)
				(= ticks 18)
			)
			(4
				(merchant setCycle: BegLoop self)
			)
			(5
				(merchant stopUpd:)
				(= ticks 60)
			)
			(6
				(if (not (Btst fMetMerchant))
					(messager say: N_MERCHANT NULL C_EMOTIONAL 4 self)
					(Bset fMetMerchant)
				else
					(messager say: N_MERCHANT NULL C_EMOTIONAL 5 self)
				)
			)
			(7
				(shameen setScript: shameenClaps)
				(self dispose:)
			)
		)
	)
)

(instance rentARoom of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (Btst fSittingAtInn)
					(ego setScript: egoStands)
					(HandsOff)
					(if (== (shema script?) comingOut)
						(comingOut changeState: 11)
					)
				else
					(= ticks 1)
				)
			)
			(1
				(if (Btst fSittingAtInn)
					(= seconds 15)
				else
					(= ticks 1)
				)
				(if (Btst fSleptAtInn)
					(self changeState: 5)
				else
					(SolvePuzzle f301RentRoom 1)
					(if (Btst fShameenStands)
						(self cue:)
					else
						(shameen
							setLoop: 4
							cel: 0
							cycleSpeed: 6
							setCycle: EndLoop self
						)
						(Bset fShameenStands)
					)
				)
			)
			(2
				(HandsOff)
				(messager say: N_SHAMEEN NULL C_RENTROOM)
				(shameen setCycle: BegLoop self)
			)
			(3
				(Bclr fShameenStands)
				(NormalEgo)
				(ego ignoreActors: illegalBits: 0)
				(if (ego inRect: 239 25 319 133)
					(ego setMotion: PolyPath (ego x?) 120 self)
				else
					(= cycles 1)
				)
			)
			(4
				(ego setMotion: PolyPath 105 100 self)
			)
			(5
				(Bset fSleptAtInn)
				(cast eachElementDo: #dispose)
				(curRoom drawPic: 400 (| BLACKOUT IRISOUT))
				(self cue:)
			)
			(6
				(Bclr fRentedRoom)
				(curRoom newRoom: 302)
			)
		)
	)
)

(instance comingOut of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((== foodOrdered mealORDERED)
						(shema view: 304)
						(movingFood init:)
					)
					((== drinkOrdered mealORDERED)
						(shema view: 304)
					)
					(else
						(shema view: 303)
					)
				)
				(shema posn: 160 77 setLoop: 0)
				(HandsOff)
				(InnMusic stop: number: 50 play:)
				(SwDoor cel: 0 setCycle: EndLoop self)
			)
			(1
				(shema setMotion: MoveTo 160 122 self)
				(Bset fShemaAsks)
			)
			(2
				(SwDoor setCycle: BegLoop)
				(shema setMotion: MoveTo 163 149 self)
			)
			(3
				(SwDoor cel: 0 stopUpd:)
				(= ticks 1)
			)
			(4
				(if (and (== foodOrdered mealNOTHING) (== drinkOrdered mealNOTHING))
					(messager say: N_SHEMA NULL C_MEETSHEMA)
					(HandsOn)
					(User canControl: FALSE)
				else
					(messager say: N_SHEMA NULL C_BRINGORDER)
					(cond 
						((== foodOrdered mealORDERED)
							(= foodOrdered mealATTABLE)
							(shema loop: 2 cel: 0 setCycle: CycleTo 3 1 self)
						)
						((== drinkOrdered mealORDERED)
							(= drinkOrdered mealATTABLE)
							(shema loop: 2 cel: 0 setCycle: CycleTo 3 1 self)
						)
						(else
							(= ticks 1)
						)
					)
				)
			)
			(5
				(shema cel: 4)
				(if (== foodOrdered mealATTABLE)
					(Food ignoreActors: setPri: 14 cel: 1 init: stopUpd:)
					(movingFood hide:)
				)
				(if (== drinkOrdered mealATTABLE)
					(Drink ignoreActors: setPri: 14 init: stopUpd:)
				)
				(Bset fShemaBringsOrder)
				(= seconds 3)
			)
			(6
				(if (or (== foodOrdered mealATTABLE) (== drinkOrdered mealATTABLE))
					(shema setCycle: EndLoop self)
				)
			)
			(7
				(HandsOff)
				(shema
					view: 304
					loop: 3
					cel: 0
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(8
				(= ticks 90)
			)
			(9
				(shema setCycle: BegLoop self)
			)
			(10
				(= ticks 60)
			)
			(11
				(client
					view: 303
					cycleSpeed: 6
					setCycle: Walk
					setScript: goingIn
				)
				(self dispose:)
			)
		)
	)
)

(instance comingToClear of Script
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 180))
			(1
				(shema
					view: 303
					setLoop: 0
					setCycle: Walk
					posn: 160 77
					cel: 0
				)
				(HandsOff)
				(InnMusic stop: number: 50 play:)
				(SwDoor cel: 0 setCycle: EndLoop self)
			)
			(2
				(shema setMotion: MoveTo 160 122 self)
				(Bset fShemaAsks)
			)
			(3
				(SwDoor setCycle: BegLoop)
				(shema setMotion: MoveTo 163 149 self)
			)
			(4
				(SwDoor cel: 0 stopUpd:)
				(= ticks 1)
			)
			(5
				(messager say: N_SHEMA NULL C_CLEARTABLE)
				(shema view: 304 loop: 2 cel: 5 setCycle: CycleTo 3 -1 self)
			)
			(6
				(if (== foodOrdered mealFINISHED)
					(Food dispose:)
					(movingFood loop: 5 cel: 2 show:)
				else
					(Drink dispose:)
				)
				(shema setCycle: BegLoop self)
			)
			(7
				(shema
					view: 304
					loop: 0
					cel: 0
					cycleSpeed: 6
					setCycle: Walk
				)
				(if (== foodOrdered mealFINISHED)
					(= foodOrdered mealCLEARED)
				else
					(= drinkOrdered mealCLEARED)
				)
				(shema setScript: goingIn)
			)
		)
	)
)

(instance goingIn of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bset fShemaBringsOrder)
				(Bset fHaveOrdered)
				(InnMusic stop: number: 50 play:)
				(if (== foodOrdered mealCLEARED)
					(movingFood dispose:)
					(= foodOrdered mealNOTHING)
				)
				(if (== drinkOrdered mealCLEARED)
					(= drinkOrdered mealNOTHING)
				)
				(shema
					loop: 1
					ignoreActors:
					setCycle: Walk
					setMotion: PolyPath 160 99 self
				)
			)
			(1
				(SwDoor cel: 0 setCycle: EndLoop self)
			)
			(2
				(shema setMotion: MoveTo 160 77 self)
			)
			(3
				(Bclr fHaveOrdered)
				(SwDoor setCycle: BegLoop self)
			)
			(4
				(shema loop: 0)
				(Bclr fShemaBringsOrder)
				(SwDoor cel: 0 stopUpd:)
				(HandsOn)
				(shema posn: 500 500)
				(if (Btst fSittingAtInn)
					(User canControl: FALSE)
				)
				(if (or (== foodOrdered mealORDERED) (== drinkOrdered mealORDERED))
					(= seconds 15)
				else
					(= ticks 1)
				)
			)
			(5
				(if (or (== foodOrdered mealORDERED) (== drinkOrdered mealORDERED))
					(shema setScript: comingOut)
				)
				(= ticks 1)
			)
			(6
				(if (or (== foodOrdered mealORDERED) (== drinkOrdered mealORDERED))
					(shema setScript: comingOut)
				else
					(shema stopUpd: setScript: 0)
					(HandsOn)
					(User canControl: TRUE)
					(User canInput: TRUE)
				)
				(= ticks 1)
			)
		)
	)
)

(instance sitScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= saveSpeed (ego cycleSpeed?))
				(HandsOff)
				(messager say: N_ROOM NULL C_SITDOWN 1 self)
			)
			(1
				(ego setMotion: PolyPath 185 173 self)
			)
			(2
				(ego
					view: 301
					setLoop: 5
					cel: 0
					priority: 14
					cycleSpeed: 12
					signal: 16
				)
				(= ticks 18)
			)
			(3
				(ego setCycle: EndLoop self)
				(Bset fSittingAtInn)
				(User canControl: FALSE)
				(if (and (Btst fMerchantIsAtInn) (not sittingDown))
					(= sittingDown TRUE)
					(merchant setScript: emotionalMerchant)
				else
					(shameen setScript: shameenClaps)
				)
				(egoOnChair init:)
			)
			(4
				(ego setCycle: 0)
				(self dispose:)
			)
		)
	)
)

(instance egoStands of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego view: 301 loop: 5 cel: 7 priority: 14)
				(= ticks 1)
			)
			(1
				(ego setCycle: BegLoop self)
				(egoOnChair dispose:)
			)
			(2
				(NormalEgo loopNW)
				(ChangeGait MOVE_WALK FALSE)
				(= ticks 1)
			)
			(3
				(ego
					cycleSpeed: saveSpeed
					moveSpeed: saveSpeed
					setMotion: PolyPath 235 175 self
				)
			)
			(4
				(Bclr fSittingAtInn)
				(= foodOrdered mealNOTHING)
				(= drinkOrdered mealNOTHING)
				(= hadDrink FALSE)
				(if (!= (shema script?) goingIn)
					(HandsOn)
					(User canControl: TRUE)
					(User canInput: TRUE)
				)
				(ego illegalBits: cWHITE ignoreActors: 0 setScript: 0)
				(if (== (shameen script?) rentARoom)
					(rentARoom cue:)
				)
			)
		)
	)
)

(instance giveThanks of Script	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== merchantState 3)
					(messager say: N_MERCHANT V_MONEY NULL 1 self)
				else
					(messager say: N_MERCHANT V_MONEY NULL 7 self)
				)
			)
			(1
				(if (== merchantState 3)
					(messager say: N_MERCHANT V_MONEY NULL 4 self)
				else
					(messager say: N_MERCHANT V_MONEY NULL 5 self)
				)
			)
			(2
				(messager say: N_MERCHANT NULL C_EMOTIONAL 1 self)
			)
			(3
				(messager say: N_MERCHANT NULL C_EMOTIONAL 2 self)
			)
			(4
				(messager say: N_MERCHANT NULL C_EMOTIONAL 3 self)
			)
			(5
				(client setScript: 0)
				(self dispose:)
			)
		)
	)
)

(instance egoOnChair of Feature
	(properties
		x 187
		y 255
		z 100
		nsTop 134
		nsLeft 172
		nsBottom 181
		nsRight 201
	)
	
	(method (init)
		(super init: &rest)
		(directionHandler add: self)
	)
	
	(method (dispose)
		(directionHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if
			(and
				(not (== (event message?) nullEvt))
				(== (theIconBar curIcon?) (theIconBar walkIconItem?))
				(& (event type?) direction)
			)
			(if (and (!= drinkOrdered mealORDERED) (!= foodOrdered mealORDERED))
				(ego setScript: egoStands)
				(if (== (shema script?) comingOut)
					(shema setScript: goingIn)
				)
			else
				(messager say: N_ROOM V_STAND C_WAITFORSHEMA)
			)
			(event claimed: TRUE)
			(return)
		else
			(super handleEvent: event)
		)
	)
	
	(method (doVerb theVerb)
		(return
			(if (== theVerb V_WALK)
				(ego setScript: egoStands)
				(if (== (shema script?) comingOut)
					(shema setScript: goingIn)
				)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
)
