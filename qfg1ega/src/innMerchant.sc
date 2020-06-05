;;; Sierra Script 1.0 - (do not remove this comment)
(script# 166)
(include game.sh)
(use Main)
(use Motion)
(use Actor)
(use System)

(public
	merchant 0
)

(local
	local0
	chatMerchant
	merchantEmotes
)
(procedure (InvalidTopic)
	(HighPrint 166 0)
	;"I am sorry, but I'm much too broken to talk about such things right now.  I have lost everything!"
	;
)

(instance merchant of Prop
	(properties
		view vInnCast
		loop 1
	)
	
	(method (init)
		(self posn: 109 129 setPri: 12 ignoreActors: stopUpd:)
		(mLegs init:)
		(mMug init:)
		(addToPics add: mLegs mMug doit:)
		(super init:)
	)
	
	(method (doit)
		(if
		(and (ego inRect: 40 135 160 180) (not local0))
			(= local0 1)
			(merchant setScript: emotionalMerchant)
		)
		(super doit:)
	)
	
	(method (dispose)
		(mLegs dispose:)
		(mMug dispose:)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(mouseDown
				(if (MouseClaimed self event shiftDown)
					(HighPrint 166 1)
					;The merchant is a rather fat man with a small moustache and beard.  He wears a turban.
					)
			)
			(saidEvent
				(if (ego inRect: 40 135 160 180)
					(if
						(not
							(if (Btst SHEMA_ASKS_ORDER)
								(< ((ScriptID 301 2) distanceTo: ego) 30)
							)
						)
						(cond 
							((Said 'gave,offer/breakfast,food,ration')
								(cond 
									((== foodOrdered mealATTABLE)
										(SolvePuzzle POINTS_GIVEABDULAFOOD 2)
										(= merchantEmotes 8)
										(= foodOrdered mealFINISHED)
										(HighPrint 166 2)
										;The merchant consumes your meal in record time.
										(= teaOrdered mealFINISHED)
										(HighPrint 166 3)
										;He then procedes to finish your drink in one gulp.
										(emotionalMerchant changeState: 1)
									)
									((ego has: iRations)
										(SolvePuzzle POINTS_GIVEABDULAFOOD 2)
										(HighPrint 166 4)
										;The merchant gratefully accepts your food ration, and consumes it in record time.
										(-- [invNum iRations])
										(= merchantEmotes 8)
										(emotionalMerchant changeState: 1)
									)
									(else
										(HighPrint 166 5)
										;You can't give what you don't have.
										)
								)
							)
							((Said 'gave,offer/drink')
								(if (== teaOrdered mealATTABLE)
									(SolvePuzzle POINTS_GIVEABDULAFOOD 2)
									(= merchantEmotes 8)
									(= teaOrdered mealFINISHED)
									(HighPrint 166 6)
									;The merchant finishes your drink in one gulp.
									(emotionalMerchant changeState: 1)
								else
									(HighPrint 166 5)
									;You can't give what you don't have.
								)
							)
							((Said 'gave,offer/alm,silver,gold')
								(if (GiveMoney 1)
									(GiveMoney 9)
									(SolvePuzzle POINTS_GIVEABDULAFOOD 2)
									(= merchantEmotes 8)
									(emotionalMerchant changeState: 1)
								)
							)
							((Said 'chat/man,abdulla')
								(HighPrint 166 0)
								;"I am sorry, but I'm much too broken to talk about such things right now.  I have lost everything!"
								)
							((Said 'look/turban')
								(HighPrint 166 7)
								;It is the merchant's hat.
								)
							((Said 'look/hat')
								(HighPrint 166 8)
								;It is the merchant's turban.
								)
							((Said 'ask>')
								(= chatMerchant TRUE)
								(cond 
									((Said '//bandit,robbery') (= merchantEmotes 1) (emotionalMerchant changeState: 1))
									(
									(or (Said '//leader') (Said '//face,voice<leader'))
									(HighPrint 166 9)
									;"There were about twelve brigands, including a Minotaur, if you can believe it!  Their leader was wearing a hooded cloak.
									;I could not see the face, but he had a high-pitched voice.  There was also some sort of warlock who giggled a lot."
									)
									((Said '//cloak')
										(HighPrint 166 10)
										;"Although worn with age, the brigand leader's cloak must have, at one time, been an exquisite garment.
										;No doubt it was stolen, as were all of my goods!"
										)
									((Said '//warlock,mage')
										(HighPrint 166 11)
										;"Perhaps it was my eyes that deceived me, but I could swear there was Gnome blood in that odd magician."
										)
									((Said '//wizard')
										(HighPrint 166 12)
										;"The magician who travels with the brigands is no wizard."
										)
									((Said '//turban') (= chatMerchant FALSE)
										(HighPrint 166 13)
										;"It is my hat."
										)
									((Said '//hat') (= chatMerchant FALSE)
										(HighPrint 166 14)
										;"It is my turban."
										)
									((Said '//name,abdulla')
										(HighPrint 166 15)
										;"I am Abdulla Doo, son of Ali, grandson of Hasan, and formerly Master Merchant of Shapeir.
										;Now I am but a penniless burden upon my friends."
										)
									((Said '//friend')
										(HighPrint 166 16)
										;"Even though they, too, lost a fortune when the brigands stole from me, Shameen and Shema are caring for this frail shadow of a great man."
										)
									((Said '//ali,dad,grandfather')
										(HighPrint 166 17)
										;"Oh, I cannot bear to talk about my family at this sad time!"
										)
									((Said '//desert,home,sand,sun') (= merchantEmotes 4) (emotionalMerchant changeState: 1))
									((Said '//danger,time')
										(HighPrint 166 18)
										;"You must not have heard of the brigands who prey upon the innocent and unwary."
										)
									((Said '//drygoods,possession')
										(HighPrint 166 19)
										;"All that I once owned is gone, alas!"
										)
									((Said '//valley,pass')
										(HighPrint 166 20)
										;"Who would have thought that there could be such trouble entering this valley during the off-season?"
										)
									(
										(Said
											'//katta,cat,shema,shameen,kindness,innkeeper,owner,keeper'
										)
										(HighPrint 166 21)
										;"My good friends Shema and Shameen, owners of this inn, are the finest of all the Katta people that ever graced Shapeir!
										;But for their kindness, I would have starved long since."
									)
									((Said '//magic') (= merchantEmotes 6) (emotionalMerchant changeState: 1))
									((Said '//guard,assistant') (= merchantEmotes 7) (emotionalMerchant changeState: 1))
									(
									(or (Said '//bull,bull') (Said '//head<bull,bull'))
									(HighPrint 166 22)
									;"If you have never seen such a beast before, you may count yourself lucky.  It is like a giant of a man with a bull's head."
									)
									((Said '//*') (= chatMerchant FALSE) (InvalidTopic))
								)
								(if chatMerchant (SolvePuzzle POINTS_TALKTOABDULA 5))
							)
						)
					)
				)
			)
		)
	)
)

(instance emotionalMerchant of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(merchant setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(1
				(merchant setLoop: 0 cel: 0 setCycle: EndLoop)
				(= cycles 5)
			)
			(2
				(merchant setLoop: 0 cel: 2)
				(= cycles 2)
			)
			(3
				(merchant setLoop: 0 cel: 3)
				(= cycles 2)
			)
			(4
				(merchant setCycle: BegLoop self)
			)
			(5
				(merchant setLoop: 1 cel: 4 stopUpd:)
				(= cycles 2)
			)
			(6
				(switch merchantEmotes
					(0 (self changeState: 7))
					(1
						(HighPrint 166 23)
						;"Two weeks ago, I was about to become wealthy beyond my dreams.
						;I, Abdulla Doo, would be the first merchant into the valley this year.  But my life was shattered by those vile brigands!"
						(++ merchantEmotes)
						(self changeState: 1)
					)
					(2
						(HighPrint 166 24)
						;"The band of brigands ambushed us just as we crossed the pass into the valley. They first used some magic which blinded us.
						;Then they overwhelmed my six guards and my assistants."
						(++ merchantEmotes)
						(self changeState: 1)
					)
					(3 (HighPrint 166 25)
						;"All of my trade goods were taken from me, right before my very eyes!  Why the brigand leader spared my life, I do not know.
						;I am now but a beggar, living off the generosity of my friends."
						)
					(4
						(HighPrint 166 26)
						;"Ah, Shapeir!  Beautiful land of golden sands and shining sun! The heart of civilization!
						;Alas, she is plagued with fierce Djinni and Efreets, who seek to drive all men and Kattas from the land."
						(++ merchantEmotes)
						(self changeState: 1)
					)
					(5
						(HighPrint 166 27)
						;"But I can speak no more of the homeland I shall never see again.  Instead, I will die in this cold, forsaken land, bereft of all I love!"
						)
					(6
						(HighPrint 166 28)
						;"Alas, had I known magic, perhaps I could have turned the tide against those abominable brigands.
						;Their magic did not seem so strong, but they outnumbered my poor caravan by two to one!"
						)
					(7
						(HighPrint 166 29)
						;"My guards are all gone, killed or run away like dogs!
						;I, too, would be lying dead had not the strange brigand leader spared my life, for what reason I know not!"
						)
					(8
						(HighPrint 166 30)
						;"Your kindness overwhelms me.  I can tell you will someday be a great hero."
						(if (not (Btst GENEROUS_TO_ABDULLA))
							(= merchantEmotes 9)
							(Bset GENEROUS_TO_ABDULLA)
							(self changeState: 1)
						)
					)
					(9
						(HighPrint 166 31)
						;"Now you will have a secret.  Among the items they stole from me was a magic rug.  I alone know the words to command it."
						(= merchantEmotes 10)
						(self changeState: 1)
					)
					(10
						(HighPrint 166 32)
						;"If you help recover my treasure, I will take you, Shameen, and Shema back to our land by way of the flying carpet.
						;Shapeir needs powerful heroes, too!"
						)
				)
			)
			(7
				(merchant stopUpd:)
				(if (not (Btst MET_ABDULLA))
					(Bset MET_ABDULLA)
					(HighPrint 166 33)
					;"Oh, it is indeed sad and dangerous times we live in when a man who struggles daily to keep from starving should be robbed of all his earthly possessions!"
				else
					(HighPrint 166 34)
					;"Hello again, my friend."
				)
			)
		)
	)
)

(instance mLegs of PicView
	(properties
		y 148
		x 110
		view vInnCast
		loop 3
	)
)

(instance mMug of PicView
	(properties
		y 131
		x 96
		view vInn
		loop 5
		cel 3
		priority 12
	)
)
