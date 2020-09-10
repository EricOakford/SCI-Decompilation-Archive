;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use Actor)

(public
	rm1 0
)

(local
	vaporCalc
	currentPage
	totalPages =  10
	[local3 3]
	saveBits1
	saveBits2
	saveBits3
	calcOn
	titleText = [
		0
		{__Fun-Seekers Guide to\n___Eastern Madera County}
		{_____The World Famous\n________Talking Bear}
		{_________Bass Lake}
		{___________Yosemite\n________National Park}
		{____The Fresno River}
		{___Sierra On-Line Building}
		{_____Elderberry House\n________Restaurant}
		{____Corlieu Falls}
		{___Deadwood Lookout}
		{__The California Grizzly}
		]
	leftText = [
		0
		{Simply click the 'Next' & 'Last' buttons to see the many exciting attractions that Eastern Madera County has to offer.\n\n
		If you don't have a mouse, simply press the space bar to go to the next page.}
		
		{Perhaps the most exciting attraction in Eastern Madera County is The World Famous Talking Bear.
		__Millions are drawn to this friendly fiberglass carnivore, which was constructed in 1923 as a lure for the
		International Olympic Site Selection Committee.__Although that years' Olympics weren't held in this area, 
		The World Famous Talking Bear has delighted millions ever since. The builders of The World Famous Talking Bear 
		couldn't have known what a heritage}
		
		{Nestled in the Sierra foothills is pristine Bass Lake.__This relaxing spot is a favorite stopover for millions 
		of vacationers on their way to visit The World Famous Talking Bear, Eastern Madera County's most exciting attraction.
		__Nestled in a foothills intersection, The World Famous Talking Bear is the only simulated large mammal ever to win the 
		coveted 'Simulated Large Mammal' award.__Another talking bear (not world famous) was being considered for the prestigious}
		
		{It has been called the most famous of all national parks, and rightly so, due to its proximity to The World Famous Talking Bear.__
		The natural wonders of the park, as awesome as they are, pale in comparison to the technological marvels of The World Famous Talking Bear.__
		Constructed of lovingly hand-crafted fiberglass on a brilliantly designed steel frame, The World Famous Talking Bear has weathered years of 
		relentless pounding by the elements.__His heart-warming taped message} {Flowing serenely through the town of Oakhurst, the Fresno River is 
		less than 100 yards from The World Famous Talking Bear. Many people have erected talking bears along the mighty Fresno, but none as famous 
		as The World Famous Talking Bear. Recently, there has been talk of constructing a canal that would link the Fresno River to major state 
		waterways. This would allow those in the shipping industry to enjoy The World Famous Talking Bear during the course of their work day. Just}
		
		{Far from the bustle of Silicon Valley is Sierra On-Line, Inc., a maker of personal computer software.__They are perhaps best known for their 
		'The World Famous Talking Bear' line of products, with such titles as 'The World Famous Talking BearWord', 
		'The World Famous Talking BearSpeller', and 'The World Famous Talking BearMoney'.__They also boast the award-winning 
		'The World Famous Talking Bear Quest' series.___With such diversity in their product line, is it any wonder that they have been} 
		
		{Those who seek that rare dining experience will savor the elegant atmosphere of the Elderberry House.__
		The hilltop location of this lavish eatery, beautifully built in the French Provencial style, affords one of the best possible views 
		of The World Famous Talking Bear.__Few people know that The World Famous Talking Bear was the first drive-thru restaurant in California.__
		Travellers came from miles around to pull up and speak their order into his fearsome fangs, then swing around}
		
		{Corlieu Falls is the tallest waterfall in Eastern Madera County.__The beauty of this natural wonder is a must-see for every vacationer.___
		Controversy rages concerning a plan to build a hydro electric plant on the falls to provide power for The World Famous Talking Bear.___
		Proponents of the plan argue that in order for The World Famous Talking Bear to be truly World Famous, he must be totally self-sufficient.___
		However, a vocal minority feels that The World Famous}
		
		{Atop lofty Deadwood mountain is the CDF lookout station.__As the area grew, people realized that they needed to protect 
		The World Famous Talking Bear from the ravages of wildfire.__From their windswept peak, vigilant rangers keep a watchful 
		eye on our rotund friend.__Because of their dedication, a raging fire that swept through the area in the early part of 
		this decade was diverted away from The World Famous Talking Bear and toward downtown Oakhurst.___The horror of the carnage} 
		
		{This area was once known for its large bear population.___However, their migration route interfered with the construction 
		of The World Famous Talking Bear, and it was necessary to hunt them to extinction.__There are those who mourn the loss, but 
		some good came out of the destruction.__It was discovered that a compound in the eyeballs of the dead bears was useful in 
		making fiberglass, and it was used in the construction of our own world-renowned signpost.}
		]
	rightText = [
		0
		{_}
		
		{they were leaving behind for the millions who visit to photograph their children atop the pleasantly snarling symbol of this, 
		our mountain area.}
		{award, but was swept away in the great Montana duststorm of 1957, so The World Famous Talking Bear stands alone as the vacation 
		spot of choice for millions.}
		{spins on, from the first hair-raising "GRRRR!" to the final "Come on in and let's talk real estate".}
		{another way for more and more people to experience the pleasant ambience of The World Famous Talking Bear.}
		{recognized again and again by The World Famous Talking Bear Boosters, a local non-profit organization dedicated
		to raising public awareness of The World Famous Talking Bear.} {to his other end to receive their food.__
		The decline in the food-service industry prompted the owners of The World Famous Talking Bear to turn him 
		into the noted tourist attraction that he is today.} {Talking Bear can stand on his own merits.__
		Whatever the outcome, The World Famous Talking Bear will remain a thrill for young and old alike.}
		
		{turned to joy as people learned that their much-loved landmark was left unscorched.__
		Residents sleep better knowing the razor-clawed rascal is safe from this insidious enemy.}
		{So, happily, their deaths were not in vain, bringing joy to millions as they listen to a brief, yet moving, 
		history of the now extinct California Grizzly.}
		]
)
(procedure (DoDisplay)
	(Display 1 0
		p_restore saveBits1
	)
	(Display 1 0
		p_restore saveBits2
	)
	(Display 1 0
		p_restore saveBits3
	)
	(= saveBits1
		(Display
			[titleText currentPage]
			p_at 3 10
			p_width 145
			p_back vWHITE
			p_font 3
			p_save
		)
	)
	(= saveBits2
		(Display
			[leftText currentPage]
			p_at 3 35
			p_width 145
			p_back vWHITE
			p_font 3
			p_save
		)
	)
	(= saveBits3
		(Display
			[rightText currentPage]
			p_at 155 110
			p_width 145
			p_back vWHITE
			p_font 3
			p_save
		)
	)
)

(instance rm1 of Room
	(properties
		picture 1
	)
	
	(method (init)
		(Load VIEW 0)
		(Load VIEW 1)
		((= vaporCalc (Prop new:))
			view: 1
			loop: 1
			cel: 1
			ignoreActors:
			setPri: 15
			posn: 100 100
			init:
		)
		(super init:)
		(addToPics add: bear rocks sine)
		(addToPics doit:)
		(controls add: LButton NButton eachElementDo: #init draw:)
		(= currentPage 1)
		(DoDisplay)
	)
	
	(method (doit)
		(super doit:)
		(if (== vaporCalcCued TRUE)
			(= vaporCalcCued FALSE)
			(= calcOn TRUE)
			(vaporCalc cel: 0)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(if (== calcOn TRUE)
			(event claimed: TRUE)
			(= calcOn FALSE)
			(vaporCalc cel: 1)
		)
		(super handleEvent: event)
	)
)

(instance bear of PicView
	(properties
		y 76
		x 230
		priority 4
		signal ADDTOPIC
	)
)

(instance rocks of PicView
	(properties
		y 87
		x 234
		loop 1
		priority 5
		signal ADDTOPIC
	)
)

(instance sine of PicView
	(properties
		y 95
		x 288
		loop 2
		priority 6
		signal ADDTOPIC
	)
)

(instance LButton of DIcon
	(properties
		state TRUE
		nsTop 176
		nsLeft 235
		view 1
	)
	
	(method (doit)
		(if (< (-- currentPage) 1) (= currentPage totalPages))
		(DoDisplay)
	)
)

(instance NButton of DIcon
	(properties
		state TRUE
		nsTop 176
		nsLeft 275
		key 32
		view 1
		cel 1
	)
	
	(method (doit)
		(if (> (++ currentPage) totalPages) (= currentPage 1))
		(DoDisplay)
	)
)
